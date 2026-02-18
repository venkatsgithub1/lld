package io.ven.hasher;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ConsistentHasher consistentHasher = new ConsistentHasher(3, List.of(1, 2, 3, 4, 5, 6, 7));
    }
}
