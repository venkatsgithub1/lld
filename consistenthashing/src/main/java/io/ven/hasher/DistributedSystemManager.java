package io.ven.hasher;

import java.util.List;

public class DistributedSystemManager {
    private final ConsistentHasher<PhysicalServer> consistentHasher;
    private final int replicationFactor;

    public DistributedSystemManager(ConsistentHasher<PhysicalServer> consistentHasher, int replicationFactor) {
        this.consistentHasher = consistentHasher;
        this.replicationFactor = replicationFactor;
    }

    public void putData(String key, String value) {
        List<PhysicalServer> replicaList = consistentHasher.getReplicaList(key, replicationFactor);
        for (PhysicalServer server : replicaList) {
            System.out.println("Storing key: " + key + " on server: " + server.name);
            server.put(key, value);
        }
    }
}
