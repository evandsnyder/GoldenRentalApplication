package com.friendlygeek.goldenrental.model.services.factory;

import com.friendlygeek.goldenrental.model.services.IService;
import com.friendlygeek.goldenrental.model.services.exception.ServiceLoadException;
import com.friendlygeek.goldenrental.model.services.manager.PropertyManager;

import java.lang.reflect.Constructor;

public class ServiceFactory implements IServiceFactory {
    PropertyManager propManager;

    public ServiceFactory(PropertyManager propManager) {
        this.propManager = propManager;
    }

    public IService getService(String service) throws ServiceLoadException {
        try {
            Class<?> c = Class.forName(getRuntimeImplementationName(service));
            Constructor<?> ctor = c.getConstructor();
            return (IService) ctor.newInstance();
        } catch (Exception e) {
            throw new ServiceLoadException("Could not load " + service, e);
        }
    }

    private String getRuntimeImplementationName(String serviceName) {
        return propManager.getProperty(serviceName);
    }
}
