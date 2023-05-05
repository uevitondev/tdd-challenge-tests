package com.devsuperior.bds02.services.exceptions;

public class ResourceNotFoundServiceException extends RuntimeException {

    public ResourceNotFoundServiceException(String msg) {
        super(msg);
    }
}
