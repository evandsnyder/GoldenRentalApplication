package com.friendlygeek.goldenrental.model.services.exception;

public class PropertyFileNotFoundException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 2409224851873481387L;

    public PropertyFileNotFoundException(final String inMessage, final Throwable inNestedException) {
        super(inMessage, inNestedException);
    }
}
