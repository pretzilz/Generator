package PolygonGenerator;
import java.util.*;
public class Polygon {
    public ArrayList<Vertex> Vertices;  //this needs to be sorted by x coordinate
    public int numVertices;
    public Polygon(int numVertices) {
        this.numVertices = numVertices;
        this.Vertices = new ArrayList<Vertex>();
        for (int i = 0; i < numVertices; i++) {
            this.Vertices.add(new Vertex());
        }
        
        Collections.sort(this.Vertices, new Comparator<Vertex>() {
            @Override
            public int compare(Vertex v1, Vertex v2) {
                return v1.compareTo(v2);    //compares based on x coordinate
            }
        });

        /*for (int i = 0; i < this.Vertices.size(); i++) {
            System.out.println(this.Vertices.get(i).toString());
        }*/
    }

    public Vertex getVertex(int index) {
        return this.Vertices.get(index);
    }


    //VISIBLITY SET HELPERS ==================================
    //Page 11
    public Make_Vt(int i) {
        //t = tmp;
        //Make_top(i - 1, i, )
        //this.Vertices.get(i).setUpperChild(tmp.getSibling());
    }
    
    public Make_Vb(int i) {
        //t = tmp;
        //Make_bot(i - 1, i, )
        //this.Vertices.get(i).setLowerChild(tmp.getSibling());
    }
}