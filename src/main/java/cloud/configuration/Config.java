package cloud.configuration;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.*;

import static cloud.configuration.Constants.*;

/**
 * Configuration class
 * @author boerm_m
 *
 */
public class Config {

	/* Singleton */
	private static volatile Config INSTANCE = null;

	/* Properties */
	private Properties configs;

	/**
	 * getInstance method
	 * @return instance
	 */
	public static Config getInstance() {
    	if (INSTANCE == null) {
    		INSTANCE = new Config();
    	}
    	return INSTANCE;
    }

	/**
	 * Constructor
	 */
	private Config() {
		configs = new Properties();
		try {
			String path = CONFIG_FILE;
			InputStream inStream = this.getClass().getClassLoader().getResourceAsStream(path);
			if (inStream == null)
				throw new Exception("resource not found: " + path);
			configs.loadFromXML(inStream);
		} catch (Exception ex) {
			System.out.println("Error in get configuration value!" + ex);
		}
	}
	
	/**
	 * Get values of configuration parameter as array
	 * @param configName name of configuration parameter
	 * @return values as string-array
	 */
	public String[] getConfigValuesAsArray(String configName) {
		if (configName == null || configName.length() == 0) {
			throw new IllegalArgumentException("Invalid configuration name!");
		}
		String result = "";
		try {
			result = configs.getProperty(configName);
		} catch (Exception ex) {
			System.out.println("Error in get configuration value!" + ex);
		}
		return result.split(CONFIG_SEPARATOR);
	}

	/**
	 * Get single value of configuration parameter
	 * @param configName name of configuration parameter
	 * @return value as string
	 */
	public String getConfigValue(String configName) {
		return getConfigValuesAsArray(configName)[0];
	}

	/**
	 * Get values of configuration parameter
	 * @param configName name of configuration parameter
	 * @return values as observable list
	 */
	public ObservableList<String> getConfigValues(String configName) {
		return FXCollections.observableArrayList(getConfigValuesAsArray(configName));
	}
}
