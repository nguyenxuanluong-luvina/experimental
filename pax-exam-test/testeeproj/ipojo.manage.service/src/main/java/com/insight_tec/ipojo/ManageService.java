package com.insight_tec.ipojo;

import java.util.Map;

import org.osgi.service.cm.ConfigurationAdmin;

public interface ManageService {
	public void update() throws InterruptedException;
}
