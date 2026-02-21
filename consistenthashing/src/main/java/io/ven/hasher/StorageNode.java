package io.ven.hasher;

public interface StorageNode {
    public String getName();

    public void put(String key, String value);

    public String get(String key);
}
