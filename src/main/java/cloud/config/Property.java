package cloud.config;

import java.io.*;
import java.util.*;

import cloud.log.Logger;

/**
 * Property class
 * @author boerm_m
 *
 */
public class Property {
	
	private Logger _log = Logger.getInstance();
	
	private static volatile Property INSTANCE = null;
	
	public static Property getInstance() {
    	if (INSTANCE == null) {
    		INSTANCE = new Property();
    	}
    	return INSTANCE;
    }
	
	/**
	 * Get value for property
	 * @param propName
	 * @return value as string
	 */
	public String getPropertyValue(String propFile, String propName) {
		if (propName == null || propName.length() == 0) {
			throw new IllegalArgumentException("Invalid property name!");
		}
		
		String userDir = System.getProperty("user.dir");
		String propPath = userDir + "/" + propFile;
		Properties props = new Properties();
		String result = null;
						
		try {			
			props.loadFromXML(new FileInputStream(propPath));
			result = props.getProperty(propName);
			
		} catch (Exception ex) {
			_log.error("Error in get property value!" + ex);
		} 		
		return result;
	}
}
