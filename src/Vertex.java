package PolygonGenerator;
public class Vertex {
    double xCoordinate;
    double yCoordinate;

    //pg 282
    //Vertex upc;
    //Vertex sib;

    //ArrayList<Vertex> aboveVisible;
    //ArrayList<Vertex> belowVisible;
    public Vertex(double x, double y) {
        this.xCoordinate = x;
        this.yCoordinate = y;
        //this.aboveVisible = new ArrayList<Vertex>();
        //this.belowVisible = new Arraylist<Vertex>();
    }

    public void setCoordinates(double x, double y) {
        this.xCoordinate = x;
        this.yCoordinate = y;
    }
}