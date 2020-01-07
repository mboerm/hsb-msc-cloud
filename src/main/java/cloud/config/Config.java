package cloud.config;

import java.io.*;
import java.util.*;

import cloud.log.Logger;
import static cloud.constants.Consts.*;

/**
 * Configuration class
 * @author boerm_m
 *
 */
public class Configuration {
	
	private Logger _log = Logger.getInstance();
	
	private static volatile Configuration INSTANCE = null;
	
	public static Configuration getInstance() {
    	if (INSTANCE == null) {
    		INSTANCE = new Configuration();
    	}
    	return INSTANCE;
    }
	
	/**
	 * Get value for configuration
	 * @param configName
	 * @return value as string
	 */
	private String getConfigValue(String configName) {
		if (configName == null || configName.length() == 0) {
			throw new IllegalArgumentException("Invalid configuration name!");
		}

		String userDir = System.getProperty("user.dir");
		String configPath = userDir + "/" + CONFIG_FILE;
		Properties configs = new Properties();
		String result = null;

		try {
			configs.loadFromXML(new FileInputStream(configPath));
			result = configs.getProperty(configName);

		} catch (Exception ex) {
			_log.error("Error in get configuration value!" + ex);
		}
		return result;
	}
}
