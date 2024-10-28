package com.hawolt.runtime.exception;

public class InvalidKeyException extends Exception {

    public InvalidKeyException(String key) {
        super("Specified key [" + key + "] is not present");
    }
}
