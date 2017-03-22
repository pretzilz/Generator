package PolygonGenerator;

import java.util.Random;
public class Vertex implements Comparable<Vertex> {
    private int xCoordinate;
    private int yCoordinate;

    //Visiblity set things
    private Vertex upperChild;
    private Vertex lowerChild;
    private Vertex sibling;

    public Vertex() {
        Random rand = new Random();
        this.xCoordinate = rand.nextInt(500) + 1;   //somewhere in the 500x500 expanse. we can set this to a variable later for variable window size.
        this.yCoordinate = rand.nextInt(500) + 1;
        upperChild = null;
        lowerChild = null;
        sibling = null;
    }

    public Vertex(boolean generateRandom) {
        this.xCoordinate = -1;
        this.yCoordinate = -1;
        upperChild = null;
        lowerChild = null;
        sibling = null;
    }

    public Vertex(int xCoordinate, int yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
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
        return Integer.compare(this.xCoordinate, right.xCoordinate);
    }

    public String toString() {
        return "(" + this.xCoordinate + ", " + this.yCoordinate + ")";
    }


    //VISIBILITY SET HELPERS===============================
    public void setUpperChild(Vertex v) {
        this.upperChild = v;
    }

    public Vertex getUpperChild() {
        return this.upperChild;
    }

    public void setLowerChild(Vertex v) {
        this.lowerChild = v;
    }

    public Vertex getLowerChild() {
        return this.lowerChild;
    }

    public void setSibling(Vertex v) {
        this.sibling = v;
    }

    public Vertex getSibling() {
        return this.sibling;
    }


}