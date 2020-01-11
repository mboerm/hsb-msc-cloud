package cloud.configuration;

import java.io.*;
import java.util.*;

import static cloud.configuration.Constants.*;

/**
 * Configuration class
 * @author boerm_m
 *
 */
public class Config {
	private static volatile Config INSTANCE = null;

	private Properties configs = null;

	public static Config getInstance() {
    	if (INSTANCE == null) {
    		INSTANCE = new Config();
    	}
    	return INSTANCE;
    }

    private Config() {
		configs = new Properties();
		try {
			String respath = CONFIG_FILE;
			InputStream inStream = this.getClass().getClassLoader().getResourceAsStream(respath);
			if (inStream == null)
				throw new Exception("resource not found: " + respath);
			configs.loadFromXML(inStream);
		} catch (Exception ex) {
			System.out.println("Error in get configuration value!" + ex);
		}
	}
	
	/**
	 * Get value
	 * @param configName
	 * @return value as string
	 */
	public String[] getConfigValues(String configName) {
		if (configName == null || configName.length() == 0) {
			throw new IllegalArgumentException("Invalid configuration name!");
		}
		String result = null;
		try {
			result = configs.getProperty(configName);
		} catch (Exception ex) {
			System.out.println("Error in get configuration value!" + ex);
		}
		return result.split(CONFIG_SEPARATOR);
	}
}
