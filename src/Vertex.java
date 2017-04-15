package PolygonGenerator;

import java.util.Random;
public class Vertex implements Comparable<Vertex> {
    private int xCoordinate;
    private int yCoordinate;

    public int index;

    private Vertex rightNeighbor;
    private Vertex leftNeighbor;

    public Vertex(int xCoordinate, int yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.rightNeighbor = null;
        this.leftNeighbor = null;
    }

    public Vertex() {
        this.xCoordinate = 0;
        this.yCoordinate = 0;
        this.rightNeighbor = null;
        this.leftNeighbor = null;
    }

    public void setCoordinates(int x, int y) {
        this.xCoordinate = x;
        this.yCoordinate = y;
    }
    public Vertex getLeftNeighbor() {
        return this.leftNeighbor;
    }

    public Vertex getRightNeighbor() {
        return this.rightNeighbor;
    }

    public void setLeftNeighbor(Vertex vert) {
        this.leftNeighbor = vert;
    }

    public void setRightNeighbor(Vertex vert) {
        this.rightNeighbor = vert;
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

    public boolean equals(Vertex right) {
        return this.getX() == right.getX() && this.getY() == right.getY();
    }


}