package io.ven.hasher;

import java.util.HashMap;
import java.util.Map;

public class PhysicalServer implements StorageNode {
    String name;
    Map<String, String> localStore = new HashMap<>();

    public PhysicalServer(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public void put(String key, String value) {
        localStore.put(key, value);
    }

    public String get(String key) {
        return localStore.get(key);
    }
}
