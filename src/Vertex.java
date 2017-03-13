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

    public void setLowerChild(Vertex v) {
        this.lowerChild = v;
    }

    public void setSibling(Vertex v) {
        this.sibling = v;
    }

    public Vertex getSibling() {
        return this.sibling;
    }


}