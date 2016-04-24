package com.xlb.test;

public class ServiceException extends RuntimeException {
    /**  */
    private static final long serialVersionUID = 1L;
    private String            message;              // parsed from json

    public ServiceException(String message) {
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}