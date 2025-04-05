package com.hawolt.boot;

public class BadArgumentException extends RuntimeException {
    public BadArgumentException(String reason) {
        super(reason);
    }
}
