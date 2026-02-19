package io.ven.hasher;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

// TODO: Add testing logic along with data logic.
public class ConsistentHasher<T> {
    private final TreeMap<Long, T> ring = new TreeMap<>();
    private final int numReplicas;

    public ConsistentHasher(int numReplicas, List<T> nodes) {
        this.numReplicas = numReplicas;
        for (T node : nodes) {
            addServer(node);
        }
    }

    public void addServer(T node) {
        for (int i = 0; i < numReplicas; i++) {
            long hash = generateHash(node.toString() + ":" + i);
            ring.put(hash, node);
        }
    }

    public void removeServer(T node) {
        for (int i = 0; i < numReplicas; i++) {
            long hash = generateHash(node.toString() + " " + i);
            ring.remove(hash);
        }
    }

    public T getServer(String key) {
        if (ring.isEmpty()) return null;
        long hash = generateHash(key);
        SortedMap<Long, T> tailMap = ring.tailMap(hash);
        long nodeHash = tailMap.isEmpty() ? ring.firstKey() : tailMap.firstKey();
        return ring.get(nodeHash);
    }

    public List<T> getReplicaList(String key, int replicationFactor) {
        long hash = generateHash(key);
        SortedMap<Long, T> tailMap = ring.tailMap(hash);
        Iterator<T> iterator = tailMap.values().iterator();
        if (!iterator.hasNext()) {
            iterator = ring.values().iterator();
        }
        Set<T> nodes = new HashSet<>();
        while (nodes.size() < replicationFactor && nodes.size() < ring.size()) {
            if (!iterator.hasNext()) {
                iterator = ring.values().iterator();
            }
            T node = iterator.next();
            nodes.add(node);
        }
        return nodes.stream().toList();
    }


    private long generateHash(String key) {
        long res = 0;
        try {
            MessageDigest md5instance = MessageDigest.getInstance("MD5");
            byte[] digest = md5instance.digest(key.getBytes());
            for (byte b : digest) {
                res <<= 8;
                res |= (b & 0xFF);
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return res;
    }
}
