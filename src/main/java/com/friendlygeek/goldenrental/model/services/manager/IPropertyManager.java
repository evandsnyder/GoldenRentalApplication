package com.friendlygeek.goldenrental.model.services.manager;

import com.friendlygeek.goldenrental.model.services.exception.PropertyFileNotFoundException;

public interface IPropertyManager {
    void loadProperties(String propertyFileName) throws PropertyFileNotFoundException;

    String getProperty(String key);
}
