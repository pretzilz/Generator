package PolygonGenerator;
import java.util.*;
public class Polygon {

    public ArrayList<Vertex> Vertices;  //this needs to be sorted by x coordinate
    public int numVertices;
    public Polygon(int numVertices) {
        this.numVertices = numVertices;
        this.Vertices = new ArrayList<Vertex>();

        for (int vertexIndex = 0; vertexIndex < numVertices; vertexIndex++) {
            this.Vertices[vertexIndex] = new Vertex();
        }
    }

    public static void Make_top() {

    }

    public static void Make_bottom() {

    }


    public int getTNandBN(int n) {
        
    }
}