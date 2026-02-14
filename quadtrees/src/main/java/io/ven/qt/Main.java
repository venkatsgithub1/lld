package io.ven.qt;

import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Boundary boundary = new Boundary(500, 500, 500, 500);
        QuadTree quadTree = new QuadTree(boundary);
        Random rand = new Random();
        for (int i = 0; i < 1000; i++) {
            quadTree.insert(new Point(rand.nextDouble() * 1000, rand.nextDouble() * 1000));
        }

        // assume we need to initialize damage for area of 100x100 at center 200, 200
        Boundary boundaryToDamage = new Boundary(200, 200, 50, 50);
        long startTime = System.nanoTime();
        List<Point> results = quadTree.query(boundaryToDamage);
        long endTime = System.nanoTime();

        System.out.println("Found results of size:" + results.size());
        System.out.println("time taken:" + (endTime - startTime) * 1000 + " microseconds");

        if (!results.isEmpty()) {
            System.out.println("sample point: " + results.get(0).x + ", " + results.get(0).y);
        }
    }
}
