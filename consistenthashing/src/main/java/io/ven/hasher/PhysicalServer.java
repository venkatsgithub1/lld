package io.ven.hasher;

import java.util.HashMap;
import java.util.Map;

public class PhysicalServer {
    String name;
    Map<String, String> localStore = new HashMap<>();

    public PhysicalServer(String name) {
        this.name = name;
    }

    public void put(String key, String value) {
        localStore.put(key, value);
    }

    public String get(String key) {
        return localStore.get(key);
    }
}
