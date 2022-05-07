package com.friendlygeek.goldenrental.model.services.exception;

public class ServiceLoadException extends Exception {

    private static final long serialVersionUID = 4295012739109954192L;

    public ServiceLoadException(final String inMessage, final Throwable inNestedException) {
        super(inMessage, inNestedException);
    }


}
