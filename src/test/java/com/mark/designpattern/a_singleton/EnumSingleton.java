package com.mark.designpattern.a_singleton;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

enum EnumSingleton {

    INSTANCE;

    private final Map<String, String> client;

    EnumSingleton() {
        client = new ConcurrentHashMap<>();
    }

    public static EnumSingleton getInstance() {
        return INSTANCE;
    }

    public Map<String, String> getClient() {
        return client;
    }
}
