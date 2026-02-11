package io.ven.qt;

import java.util.ArrayList;
import java.util.List;

public class QuadTree {
    private final List<Point> points;
    private final Boundary boundary;
    private boolean divided = false;
    private int capacity = 4;

    public QuadTree(Boundary boundary) {
        this.points = new ArrayList<>();
        this.boundary = boundary;
    }

    private void subdivide() {
    }

    public void insert(Point p) {
        if (points.size() < capacity) {
            points.add(p);
            return;
        }
        subdivide();
    }
}
