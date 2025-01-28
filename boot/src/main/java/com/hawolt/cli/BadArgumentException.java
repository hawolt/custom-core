package com.hawolt.cli;

public class BadArgumentException extends RuntimeException {
    public BadArgumentException(String reason) {
        super(reason);
    }
}
