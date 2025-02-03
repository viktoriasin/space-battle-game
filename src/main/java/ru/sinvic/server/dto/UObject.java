package ru.sinvic.server.dto;

public interface UObject {
    Object getValue(String valueName);
    void setValue(String valueName, Object value);
}
