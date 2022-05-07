package com.friendlygeek.goldenrental.model.services.manager;

import com.friendlygeek.goldenrental.model.services.exception.PropertyFileNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyManager implements IPropertyManager {
    private static final Logger logger = LogManager.getLogger("com.friendlygeek");
    private static Properties properties;

    public void loadProperties(String propertyFileLocation) throws PropertyFileNotFoundException {
        properties = new Properties();
        FileInputStream sf = null;

        try {
            sf = new FileInputStream(propertyFileLocation);
            properties.load(sf);
        } catch (FileNotFoundException e) {
            logger.error("Properties file not found at location " + propertyFileLocation, e);
            throw new PropertyFileNotFoundException("Property File cannot be found in PropertyManager::loadProperties", e);
        } catch (IOException e) {
            logger.error("IOException while loading property file from location: " + propertyFileLocation, e);
            throw new PropertyFileNotFoundException("IOException while loading property file in PropertyManager::loadProperties", e);
        } catch (Exception e) {
            logger.error("Exception while loading property file from location: " + propertyFileLocation, e);
            throw new PropertyFileNotFoundException("Exception while loading property file in PropertyManager::loadProperties", e);
        } finally {
            if (sf != null) {
                try {
                    sf.close();
                } catch (IOException e) {
                    logger.error(e.getClass() + ": " + e.getMessage(), e);
                }
            }
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

}
