package PolygonGenerator;

import java.util.Random;
public class Vertex implements Comparable<Vertex> {
    private int xCoordinate;
    private int yCoordinate;

    public Vertex(int xCoordinate, int yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public Vertex() {
        this.xCoordinate = 0;
        this.yCoordinate = 0;
    }

    public void setCoordinates(int x, int y) {
        this.xCoordinate = x;
        this.yCoordinate = y;
    }

    public int getX() {
        return this.xCoordinate;
    }

    public int getY() {
        return this.yCoordinate;
    }

    

    public int compareTo(Vertex right) {
        return Double.compare(this.xCoordinate, right.xCoordinate);
    }

    public String toString() {
        return "(" + this.xCoordinate + ", " + this.yCoordinate + ")";
    }


}