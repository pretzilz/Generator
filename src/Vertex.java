package PolygonGenerator;

import java.util.Random;
public class Vertex implements Comparable<Vertex> {
    private int xCoordinate;
    private int yCoordinate;

    //pg 282
    //Vertex upc;
    //Vertex sib;

    //ArrayList<Vertex> aboveVisible;
    //ArrayList<Vertex> belowVisible;
    public Vertex() {
        Random rand = new Random();
        this.xCoordinate = rand.nextInt(500) + 1;   //somewhere in the 500x500 expanse. we can set this to a variable later for variable window size.
        this.yCoordinate = rand.nextInt(500) + 1;
        //this.aboveVisible = new ArrayList<Vertex>();
        //this.belowVisible = new Arraylist<Vertex>();
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
}