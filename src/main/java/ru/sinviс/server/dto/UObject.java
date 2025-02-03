package ru.sinviс.server.dto;

public interface UObject {
    Object getValue(String valueName);
    void setValue(String valueName, Object value);
}
