package com.hawolt.sl4fj.logger;

import org.slf4j.ILoggerFactory;
import org.slf4j.IMarkerFactory;
import org.slf4j.helpers.BasicMDCAdapter;
import org.slf4j.helpers.BasicMarkerFactory;
import org.slf4j.spi.MDCAdapter;
import org.slf4j.spi.SLF4JServiceProvider;

public class SLF4JProvider implements SLF4JServiceProvider {
    private IMarkerFactory markerFactory;
    private SLF4JFactory slf4JFactory;
    private MDCAdapter mdcAdapter;

    @Override
    public ILoggerFactory getLoggerFactory() {
        return slf4JFactory;
    }

    @Override
    public IMarkerFactory getMarkerFactory() {
        return markerFactory;
    }

    @Override
    public MDCAdapter getMDCAdapter() {
        return mdcAdapter;
    }

    @Override
    public String getRequestedApiVersion() {
        return "2.0.17";
    }

    @Override
    public void initialize() {
        this.slf4JFactory = new SLF4JFactory();
        this.mdcAdapter = new BasicMDCAdapter();
        this.markerFactory = new BasicMarkerFactory();
    }
}
