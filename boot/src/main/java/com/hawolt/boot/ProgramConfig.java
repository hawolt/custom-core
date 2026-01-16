package com.hawolt.boot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProgramConfig {
    private final Map<String, Object> parameters = new HashMap<>();
    private final Map<String, String> link = new HashMap<>();
    private final String[] mandatory, optional;

    public ProgramConfig() {
        this(new String[0], new String[0]);
    }

    public ProgramConfig(String[] mandatory, String[] optional) {
        this.mandatory = mandatory;
        this.optional = optional;
    }

    public String help() {
        StringBuilder builder = new StringBuilder();
        builder.append("Mandatory arguments: ").append(String.join(", ", mandatory)).append(System.lineSeparator());
        builder.append("Optional arguments: ").append(String.join(", ", optional)).append(System.lineSeparator());
        return builder.toString();
    }

    public void link(String min, String full) {
        this.link.put(min, full);
        this.link.put(full, full);

        Object minValue = parameters.get(min);
        Object fullValue = parameters.get(full);
        Object actual = minValue != null ? minValue : fullValue;

        if (actual != null) {
            parameters.put(full, actual);
            parameters.remove(min);
        }
    }

    public void put(String name, Object value) {
        this.parameters.put(name, value);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String name) {
        String bridge = link.getOrDefault(name, name);
        return (T) parameters.get(bridge);
    }

    @SuppressWarnings("unchecked")
    public <T> T getOrDefault(String name, T value) {
        String bridge = link.getOrDefault(name, name);
        T stored = (T) parameters.get(bridge);
        return stored == null ? value : stored;
    }

    public boolean has(String name) {
        return parameters.containsKey(name) || link.containsKey(name);
    }

    public ArrayList<String> keyList() {
        return new ArrayList<>(parameters.keySet());
    }

    @Override
    public String toString() {
        return "BootParameter{" +
                "parameters=" + parameters +
                '}';
    }
}
