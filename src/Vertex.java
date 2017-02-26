package PolygonGenerator;
public class Vertex {
    double xCoordinate;
    double yCoordinate;

    //pg 282
    Vertex upc;
    Vertex sib;
    public Vertex(double x, double y) {
        this.xCoordinate = x;
        this.yCoordinate = y;
    }

    public void setCoordinates(double x, double y) {
        this.xCoordinate = x;
        this.yCoordinate = y;
    }

    //TODO get coordinates
}