package com.hawolt.logger;

public interface LogCustomizer {
    String onBeforeWrite(String line);
}
