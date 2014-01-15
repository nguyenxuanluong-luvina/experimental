package com.insight_tec.ipojo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;

/*
 * Simply class implement HelloService and ManageService (customize ,not OSGi ManageService) to 
 * update instance property at runtime
 */
public class ItemsProvider implements Items, Runnable, ManageService {
	ConfigurationAdmin pm;
	int stock;
	boolean status;
	static boolean created = false;
	Map<String, String> mapConf = null;
	static String pid;
	static final int INIT_STOCK = 5;
	public void init() throws InterruptedException {
		stock = INIT_STOCK;
		System.out.println("Initialize stock " + stock);
	}

	// method to change propety when need to reconfiguration instance
	public void fillStock(int q) {
		stock = q;
		System.out.println("stock fill to:  " + q);
	}

	public void run() {
		while (status) {
			stock--;
			try {
				System.out
						.println("[Info]-Custumer buy an item.Stock remain : "
								+ stock);
				if (stock == 0) {
					this.update();
				}
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	// Start point of instance lifecyle , when instance is validate
	public void start() throws InterruptedException {
		this.init();
		Thread t = new Thread(this);
		t.start();
		status = true;
	}

	// End of instance lifecyle ,when instance is invalidate or destroy
	public void stop() {
		System.out.println("Goodbye! Remain stock :  " + stock);
		status = false;
	}

	// Notified when the service instance is complete configuration
	public void updated() {
		System.out.println("[Notified]-Stock updated !");
	}

	// Update service instance with configuration from file
	public void update() throws InterruptedException {
		Properties props = new Properties();
		mapConf = this.processCfgFile(new File("C:\\ipojo.conf"));
		for (Map.Entry<String, String> entry : mapConf.entrySet()) {
			try {
				props.put(entry.getKey(), Integer.parseInt(entry.getValue()));
			} catch (NumberFormatException e) {
				props.put(entry.getKey(), entry.getValue());
			}
		}
		try {
			// Get pid of instance will be configured.
			// pid defined when instance created in XML metadata
			// ("managed.service.pid" property)
			if (mapConf.get("service.pid") != null) {
				pid = mapConf.get("service.pid");
			}
			// Get configuration from ConfigurationAdmin with pid
			Configuration conf = pm.getConfiguration(pid);
			conf.update(props);
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	// process configuration file to get data configuration for instance
	public Map<String, String> processCfgFile(File file) {
		Map<String, String> mapConf = new HashMap<String, String>();
		if (file.exists()) {
			try {
				BufferedReader bf = new BufferedReader(new FileReader(file));
				String line = null;
				while ((line = bf.readLine()) != null) {
					if (line.trim() != "") {
						String[] tmp = line.split("=");
						if (tmp.length == 2) {
							mapConf.put(tmp[0], tmp[1]);
						} else {
							System.out
									.println("Incorect configuration format : "
											+ line + " .Format : key=value");
						}
					}
				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return mapConf;
		} else {
			System.out.print("File " + file.getName() + " not found !");
			return null;
		}

	}
}
