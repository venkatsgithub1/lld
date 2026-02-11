package io.ven.qt;

public class Boundary {
    double x;
    double y;
    double w;
    double h;

    public Boundary(double x, double y, double w, double h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public boolean contains(Point p2) {
        return p2.x >= this.x - w && p2.x <= this.x + w && p2.y >= this.y - h && p2.y <= this.y + h;
    }
}
