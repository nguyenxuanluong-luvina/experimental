package org.wiperdog.sample.managedservice.internal;


import static org.junit.Assert.*;

import java.util.Dictionary;
import java.util.Hashtable;

import org.junit.Test;
import org.osgi.service.cm.ConfigurationException;
import org.wiperdog.sample.managedservice.ServiceIntf;

public class Test_SampleService {

	@Test
	public void test1() throws ConfigurationException {
		Dictionary prop = new Hashtable();
		SampleService svc = new SampleService();
		assertEquals(svc.getPid(), ServiceIntf.class.getPackage().getName());
		prop.put("testkey", "testvalue");
		svc.updated(prop);
		assertEquals(svc.getProperty("testkey"), "testvalue");
	}
}
