package com.hawolt.sl4fj.logger;

import org.slf4j.Logger;
import org.slf4j.Marker;

public class SLF4JLogger implements Logger {

    private final String name;

    public SLF4JLogger(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isTraceEnabled() {
        return true;
    }

    @Override
    public void trace(String s) {
        com.hawolt.logger.Logger.trace(s);
    }

    @Override
    public void trace(String s, Object o) {
        com.hawolt.logger.Logger.trace(s, o);
    }

    @Override
    public void trace(String s, Object o, Object o1) {
        com.hawolt.logger.Logger.trace(s, o, o1);
    }

    @Override
    public void trace(String s, Object... objects) {
        com.hawolt.logger.Logger.trace(s, objects);
    }

    @Override
    public void trace(String s, Throwable throwable) {
        com.hawolt.logger.Logger.trace(s);
        com.hawolt.logger.Logger.trace(throwable);
    }

    @Override
    public boolean isTraceEnabled(Marker marker) {
        return false;
    }

    @Override
    public void trace(Marker marker, String s) {

    }

    @Override
    public void trace(Marker marker, String s, Object o) {

    }

    @Override
    public void trace(Marker marker, String s, Object o, Object o1) {

    }

    @Override
    public void trace(Marker marker, String s, Object... objects) {

    }

    @Override
    public void trace(Marker marker, String s, Throwable throwable) {

    }

    @Override
    public boolean isDebugEnabled() {
        return true;
    }

    @Override
    public void debug(String s) {
        com.hawolt.logger.Logger.debug(s);
    }

    @Override
    public void debug(String s, Object o) {
        com.hawolt.logger.Logger.debug(s, o);
    }

    @Override
    public void debug(String s, Object o, Object o1) {
        com.hawolt.logger.Logger.debug(s, o, o1);
    }

    @Override
    public void debug(String s, Object... objects) {
        com.hawolt.logger.Logger.debug(s, objects);
    }

    @Override
    public void debug(String s, Throwable throwable) {
        com.hawolt.logger.Logger.debug(s);
        com.hawolt.logger.Logger.debug(throwable);
    }

    @Override
    public boolean isDebugEnabled(Marker marker) {
        return false;
    }

    @Override
    public void debug(Marker marker, String s) {

    }

    @Override
    public void debug(Marker marker, String s, Object o) {

    }

    @Override
    public void debug(Marker marker, String s, Object o, Object o1) {

    }

    @Override
    public void debug(Marker marker, String s, Object... objects) {

    }

    @Override
    public void debug(Marker marker, String s, Throwable throwable) {

    }

    @Override
    public boolean isInfoEnabled() {
        return true;
    }

    @Override
    public void info(String s) {
        com.hawolt.logger.Logger.info(s);
    }

    @Override
    public void info(String s, Object o) {
        com.hawolt.logger.Logger.info(s, o);
    }

    @Override
    public void info(String s, Object o, Object o1) {
        com.hawolt.logger.Logger.info(s, o, o1);
    }

    @Override
    public void info(String s, Object... objects) {
        com.hawolt.logger.Logger.info(s, objects);
    }

    @Override
    public void info(String s, Throwable throwable) {
        com.hawolt.logger.Logger.info(s);
        com.hawolt.logger.Logger.info(throwable);
    }

    @Override
    public boolean isInfoEnabled(Marker marker) {
        return false;
    }

    @Override
    public void info(Marker marker, String s) {

    }

    @Override
    public void info(Marker marker, String s, Object o) {

    }

    @Override
    public void info(Marker marker, String s, Object o, Object o1) {

    }

    @Override
    public void info(Marker marker, String s, Object... objects) {

    }

    @Override
    public void info(Marker marker, String s, Throwable throwable) {

    }

    @Override
    public boolean isWarnEnabled() {
        return true;
    }

    @Override
    public void warn(String s) {
        com.hawolt.logger.Logger.warn(s);
    }

    @Override
    public void warn(String s, Object o) {
        com.hawolt.logger.Logger.warn(s, o);
    }

    @Override
    public void warn(String s, Object... objects) {
        com.hawolt.logger.Logger.warn(s, objects);
    }

    @Override
    public void warn(String s, Object o, Object o1) {
        com.hawolt.logger.Logger.warn(s, o, o1);
    }

    @Override
    public void warn(String s, Throwable throwable) {
        com.hawolt.logger.Logger.warn(s);
        com.hawolt.logger.Logger.warn(throwable);
    }

    @Override
    public boolean isWarnEnabled(Marker marker) {
        return false;
    }

    @Override
    public void warn(Marker marker, String s) {

    }

    @Override
    public void warn(Marker marker, String s, Object o) {

    }

    @Override
    public void warn(Marker marker, String s, Object o, Object o1) {

    }

    @Override
    public void warn(Marker marker, String s, Object... objects) {

    }

    @Override
    public void warn(Marker marker, String s, Throwable throwable) {

    }

    @Override
    public boolean isErrorEnabled() {
        return true;
    }

    @Override
    public void error(String s) {
        com.hawolt.logger.Logger.error(s);
    }

    @Override
    public void error(String s, Object o) {
        com.hawolt.logger.Logger.error(s, o);
    }

    @Override
    public void error(String s, Object o, Object o1) {
        com.hawolt.logger.Logger.error(s, o, o1);
    }

    @Override
    public void error(String s, Object... objects) {
        com.hawolt.logger.Logger.error(s, objects);
    }

    @Override
    public void error(String s, Throwable throwable) {
        com.hawolt.logger.Logger.error(s);
        com.hawolt.logger.Logger.error(throwable);
    }

    @Override
    public boolean isErrorEnabled(Marker marker) {
        return false;
    }

    @Override
    public void error(Marker marker, String s) {

    }

    @Override
    public void error(Marker marker, String s, Object o) {

    }

    @Override
    public void error(Marker marker, String s, Object o, Object o1) {

    }

    @Override
    public void error(Marker marker, String s, Object... objects) {

    }

    @Override
    public void error(Marker marker, String s, Throwable throwable) {

    }
}