package com.friendlygeek.goldenrental.model.services.factory;

import com.friendlygeek.goldenrental.model.services.IService;
import com.friendlygeek.goldenrental.model.services.exception.ServiceLoadException;

public interface IServiceFactory {
    IService getService(String serviceName) throws ServiceLoadException;
}
