package org.wiperdog.sample.managedservice.internal;
import java.util.Dictionary;
import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.service.cm.ManagedService;
import org.wiperdog.sample.managedservice.ServiceIntf;

public class Activator implements BundleActivator {

	public void start(BundleContext context) throws Exception {
		Dictionary prop = new Hashtable();
		SampleService service = new SampleService();
		prop.put(Constants.SERVICE_PID, service.getPid());
		context.registerService(ServiceIntf.class.getName(), service, prop);
		context.registerService(ManagedService.class.getName(), service, prop);
	}

	public void stop(BundleContext context) throws Exception {
	}
	
}