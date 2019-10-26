package com.alek.influentialpeople.exception.exceptions;

public class EntityExistsException extends RuntimeException {

    public EntityExistsException(String entityAlreadyExistMessage) {
        super(entityAlreadyExistMessage);
    }

}
