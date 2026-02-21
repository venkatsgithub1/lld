package io.ven.hasher;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<PhysicalServer> nodeList = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            nodeList.add(new PhysicalServer("Server-" + i));
        }
        int replicationFactor = 3;
        ConsistentHasher<PhysicalServer> consistentHasher = new ConsistentHasher<>(replicationFactor, nodeList);
        DistributedSystemManager distributedSystemManager = new DistributedSystemManager(consistentHasher, replicationFactor);
        distributedSystemManager.putData("Homer simpson", "Springfield");

        consistentHasher.putData("Bart simpson", "Springfield", 3);
        System.out.println("Got " + consistentHasher.getData("Bart simpson", 3));
    }
}
