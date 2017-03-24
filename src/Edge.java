package PolygonGenerator;

public class Edge {
    private Vertex point1;
    private Vertex point2;

    private int aEq;
    private int bEq;
    private int cEq;

    private boolean onTopChain;

    public Edge(Vertex p1, Vertex p2, boolean onTopChain) {
        this.point1 = p1;
        this.point2 = p2;
        this.onTopChain = onTopChain;

        this.aEq = p2.getY() - p1.getY();
        this.bEq = p1.getX() - p2.getX();
        this.cEq = this.aEq * p1.getX() + this.bEq * p1.getY();
    }

    public Vertex getPoint1() {
        return this.point1;
    }

    public Vertex getPoint2() {
        return this.point2;
    }

    public boolean onTop() {
        return this.onTopChain;
    }



    public boolean intersects (Edge lhs) {
        int det = (this.aEq * lhs.bEq) - (lhs.aEq * this.bEq);
        if (det == 0) {
            return false;   //parallel
        }
        else {
            double xIntersect = ((lhs.bEq * this.cEq) - (this.bEq * lhs.cEq)) /det;
            double yIntersect = ((this.aEq * lhs.cEq) - (lhs.aEq * this.cEq)) /det;
            return this.pointOnLine(xIntersect, yIntersect) && lhs.pointOnLine(xIntersect, yIntersect); 
        }
    }

    public boolean pointOnLine(double x, double y) {
        boolean withinX = Math.min(this.point1.getX(), this.point2.getX()) <= x && x <= Math.max(this.point1.getX(), this.point2.getX());
        boolean withinY = Math.min(this.point1.getY(), this.point2.getY()) <= x && x <= Math.max(this.point1.getY(), this.point2.getY());
        return withinX && withinY;
    }

    public String toString() {
        return this.point1.toString() + " -> " + this.point2.toString();
    }

}