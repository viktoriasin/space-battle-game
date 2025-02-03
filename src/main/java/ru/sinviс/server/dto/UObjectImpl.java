package ru.sinviс.server.dto;

import java.util.Map;

public class UObjectImpl implements UObject {
    Map<String, Object> values;
    @Override
    public Object getValue(String valueName) {
        return values.get(valueName);
    }

    @Override
    public void setValue(String valueName, Object value) {
        values.put(valueName, value);
    }
}
