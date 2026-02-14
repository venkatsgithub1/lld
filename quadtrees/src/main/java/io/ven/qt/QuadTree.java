package io.ven.qt;

import java.util.ArrayList;
import java.util.List;

// for 200, 200 rectangle x, y = 100, 100 are the center respectively.
// Current quad tree implementation has the capability to find points, or a point
// that needs to be removed.
// If we want to simulate a pointed hit, it will be 2 step
// for a given x, y, 0, 0 find all points, allow player to choose a point
// they want to have removed.
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
        nw = new QuadTree(new Boundary(newX - newW, newY + newH, newW, newH));
        ne = new QuadTree(new Boundary(newX + newW, newY + newH, newW, newH));
        sw = new QuadTree(new Boundary(newX - newW, newY - newH, newW, newH));
        se = new QuadTree(new Boundary(newX + newW, newY - newH, newW, newH));
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

    public List<Point> query(Boundary range) {
        if (!this.boundary.intersects(range)) {
            return List.of();
        }

        List<Point> pointsFound = new ArrayList<>(points);

        if (this.divided) {
            pointsFound.addAll(nw.query(range));
            pointsFound.addAll(ne.query(range));
            pointsFound.addAll(sw.query(range));
            pointsFound.addAll(se.query(range));
        }

        return pointsFound;
    }
}
