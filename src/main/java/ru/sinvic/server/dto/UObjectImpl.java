package ru.sinvic.server.dto;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UObjectImpl implements UObject {
    private final Map<String, Object> values = new ConcurrentHashMap<>();

    @Override
    public Object getValue(String valueName) {
        return values.get(valueName);
    }

    @Override
    public void setValue(String valueName, Object value) {
        values.put(valueName, value);
    }
}
