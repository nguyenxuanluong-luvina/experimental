package org.wiperdog.sample.managedservice.internal;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;
import java.util.Enumeration;
import org.wiperdog.sample.managedservice.ServiceIntf;

public class SampleService implements ServiceIntf {
	private static String pid = "TestFileInstallServicePid11111";
	private static Map<String, Object> myProperties = new HashMap<String, Object>();
	
	public void updated(Dictionary arg0) {
		System.out.println("updated called with argument: " + arg0);
		if (arg0 != null) {
			Enumeration e = arg0.keys();
			while (e.hasMoreElements()) {
				String key = (String) e.nextElement();
				System.out.println("key:" + key + ", value:" + arg0.get(key));
				myProperties.put(key, arg0.get(key));
			}
		}
	}
	
	public String getPid() {
		return pid;
	}

	public Object getProperty(String key) {
		return myProperties.get(key);
	}

}
