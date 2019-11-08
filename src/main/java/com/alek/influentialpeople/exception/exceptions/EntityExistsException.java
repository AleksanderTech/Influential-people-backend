package com.alek.influentialpeople.exception.exceptions;

public class EntityExistsException extends RuntimeException {

    public EntityExistsException(String entityAlreadyExistsMessage) {
        super(entityAlreadyExistsMessage);
    }
}
