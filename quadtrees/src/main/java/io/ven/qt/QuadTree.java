package io.ven.qt;

import java.util.ArrayList;
import java.util.List;

// for 200, 200 rectangle x, y = 100, 100 are the center respectively.
public class QuadTree {
    private final List<Point> points;
    private final Boundary boundary;
    private boolean divided = false;
    private final int CAPACITY = 4;
    private QuadTree nw;
    private QuadTree ne;
    private QuadTree sw;
    private QuadTree se;

    public QuadTree(Boundary boundary) {
        this.points = new ArrayList<>();
        this.boundary = boundary;
    }

    private void subdivide() {
        double newX = boundary.x;
        double newY = boundary.y;
        double newW = boundary.w / 2;
        double newH = boundary.h / 2;
        QuadTree nw = new QuadTree(new Boundary(newX - newW, newY + newH, newW, newH));
        QuadTree ne = new QuadTree(new Boundary(newX + newW, newY + newH, newW, newH));
        QuadTree sw = new QuadTree(new Boundary(newX - newW, newY - newH, newW, newH));
        QuadTree se = new QuadTree(new Boundary(newX + newW, newY - newH, newW, newH));
        divided = true;
    }

    public boolean insert(Point p) {
        if (!boundary.contains(p)) {
            return false;
        }
        if (points.size() < CAPACITY) {
            points.add(p);
            return true;
        }
        if (!divided) {
            subdivide();
        }
        return nw.insert(p) || ne.insert(p) || sw.insert(p) || se.insert(p);
    }
}
