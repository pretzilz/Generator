package PolygonGenerator;
import java.util.*;
import java.io.*;
public class Polygon {
    public ArrayList<Vertex> Vertices;  //this needs to be sorted by x coordinate
    public int numVertices;

    public ArrayList<Vertex> TopChain;
    public ArrayList<Vertex> BottomChain;

    public ArrayList<Edge> Edges;

    private int k;
    private int largestX;
    private int smallestX;
    private int largestY;
    private int smallestY;

    private int height;
    private int width;
    public Polygon(int numVertices) {
        this.Vertices = new ArrayList<Vertex>();
        this.TopChain = new ArrayList<Vertex>();
        this.BottomChain = new ArrayList<Vertex>();
        this.Edges = new ArrayList<Edge>();
        this.largestX = Integer.MIN_VALUE;
        this.largestY = Integer.MIN_VALUE;
        this.smallestX = Integer.MAX_VALUE;
        this.smallestY = Integer.MAX_VALUE;
        this.numVertices = numVertices;
        
        generateTopChain();
        generateBottomChain();

        this.width = this.largestX - this.smallestX;
        this.height = this.largestY - this.smallestY;
    }

    private void generateTopChain() {
        int xStart = 0;
        Random rand = new Random();
        k = (numVertices/10) + rand.nextInt(((9*numVertices)/10) - (numVertices/10) + 1); //maybe ensure that k > n/10 and < 9n/10 or something. (could do +n/10 and so on)
        //compute the top chain
        for(int topChainIndex = 0; topChainIndex < k; topChainIndex++){
            //x coordinate is some random amount to the right of the previous one
            int xVal = rand.nextInt(20); //or make it (0,10) or whatever (as big as we want it to go)
            int yVal = rand.nextInt(100); //or make it (0,10) or whatever
            Vertex newVertex = new Vertex(xStart + xVal, yVal);
            Vertices.add(newVertex);
            TopChain.add(newVertex);
            xStart += xVal;

            if (xStart > largestX) {
                largestX = xStart;
            }
            if (xStart < smallestX) {
                smallestX = xStart;
            }
            if (yVal > largestY) {
                largestY = yVal;
            }
            if (yVal < smallestY) {
                smallestY = yVal;
            }
            if (topChainIndex > 0) {
                Edge newEdge = new Edge(TopChain.get(topChainIndex - 1), TopChain.get(topChainIndex), true);
                Edges.add(newEdge);
            }
        }
    }

    private void generateBottomChain() {
        int xStart = 0;
        Random rand = new Random();
        for(int bottomChainIndex = 0; bottomChainIndex < numVertices-k; bottomChainIndex++){
            //x coordinate is some random amount to the right of the previous one
            int xVal = rand.nextInt(20); //or make it (0,10) or whatever (as big as we want it to go)
            int yVal = rand.nextInt(100); //or make it (0,10) or whatever (TODO replace the 10)
            Vertex newVertex = new Vertex(xStart + xVal, yVal);
            Vertex otherVertexOfLine = new Vertex();
            if (bottomChainIndex == 0) {  //if we're at the first vertex of the bottom chain, it will be connected to the first vertex of the top chain.
                //System.out.println(isAbove(newVertex, this.TopChain.get(0), this.TopChain.get(1)));
                if(isAbove(newVertex, this.TopChain.get(0), this.TopChain.get(1))) {    //if the first vertex is above the first line, it won't work anyway.
                    bottomChainIndex--;
                    continue;
                }
                else {
                    otherVertexOfLine = this.TopChain.get(0);   //but we still have to check if it intersects
                }
            }
            else {
                otherVertexOfLine = this.BottomChain.get(bottomChainIndex - 1); //otherwise the other point is the last point we added in the bottom chain
            }
            if (!intersectsWithTopChain(otherVertexOfLine, newVertex)){
                if (bottomChainIndex == numVertices-k-1) {    //if it's the last vertex, we have to also make sure the line connecting to the last vertex of the top chain doesn't intersect
                    if (!intersectsWithTopChain(newVertex, this.TopChain.get(k-1))) {
                        Vertices.add(newVertex);
                        BottomChain.add(newVertex);
                        xStart += xVal;
                        if (xStart > largestX) {
                            largestX = xStart;
                        }
                        if (xStart < smallestX) {
                            smallestX = xStart;
                        }
                        if (yVal > largestY) {
                            largestY = yVal;
                        }
                        if (yVal < smallestY) {
                            smallestY = yVal;
                        }
                        Edge newEdge = new Edge(otherVertexOfLine, newVertex, false);
                        Edges.add(newEdge);
                        Edge lastEdge = new Edge(newVertex, this.TopChain.get(k-1), false);
                        Edges.add(lastEdge);
                    } else {
                        bottomChainIndex--;
                        continue;
                    }
                    
                }
                else {
                    Vertices.add(newVertex);
                    BottomChain.add(newVertex);
                    xStart += xVal;
                    if (xStart > largestX) {
                        largestX = xStart;
                    }
                    if (xStart < smallestX) {
                        smallestX = xStart;
                    }
                    if (yVal > largestY) {
                        largestY = yVal;
                    }
                    if (yVal < smallestY) {
                        smallestY = yVal;
                    }
                    Edge newEdge = new Edge(otherVertexOfLine, newVertex, false);
                    Edges.add(newEdge);
                }
                
            }
            else {
                bottomChainIndex--;
                continue;
            }
        }
    }



    public Vertex getVertex(int index) {
        return this.Vertices.get(index);
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    //This determines whether or not a vertex k is above the line (j1, j2)
    public boolean isAbove(Vertex k, Vertex j1, Vertex j2) {
        double slope = ((j2.getY() - j1.getY())/(j2.getX() - j1.getX()));   //get slope
        double yInt = -((slope * j1.getX()) - j1.getY());   //find y intercept
        double yCoordOfKOnLine = (slope * k.getX()) + yInt;    //get the y coordinate of k on line
        double yDiff = yCoordOfKOnLine - k.getY(); //get difference between actual y coordinate of k and the one on the line
        //if it's greater than 0, it's below the line. if it's equal, then it's on the line.
        return yDiff < 0; 

    }

    public boolean isBelow(Vertex k, Vertex j1, Vertex j2) {
        double slope = ((j2.getY() - j1.getY())/(j2.getX() - j1.getX()));   //get slope
        double yInt = -((slope * j1.getX()) - j1.getY());   //find y intercept
        double yCoordOfKOnLine = (slope * k.getX()) + yInt;    //get the y coordinate of k on line
        double yDiff = yCoordOfKOnLine - k.getY(); //get difference between actual y coordinate of k and the one on the line
        //if it's less than 0, it's above the line. if it's equal, then it's on the line.
        return yDiff > 0; 

    }


    public boolean intersectsWithTopChain(Vertex v1, Vertex v2) {   //this is horrible, but it works, i suppose
        Edge newEdge = new Edge(v1, v2, false);
        for (int topEdgeIndex = 1; topEdgeIndex < this.TopChain.size(); topEdgeIndex++) {
            Edge currentTopEdge = new Edge(this.TopChain.get(topEdgeIndex - 1), this.TopChain.get(topEdgeIndex), false);
            if (newEdge.intersects(currentTopEdge)) {
                return true;
            }
        }
        return false;
    }


    public void printToFile() {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("polygon.txt")));
            writer.write("Vertices: " + this.numVertices + "\n");
            writer.write("Top chain length: " + this.TopChain.size() + "\n");
            writer.write("Bottom chain length: " + this.BottomChain.size() + "\n");
            writer.write("=============TOP CHAIN VERTICES=============\n");
            for (int vertexIndex = 0; vertexIndex < this.TopChain.size(); vertexIndex++) {
                writer.write(this.TopChain.get(vertexIndex) + "\n");
            }
            writer.write("===========BOTTOM CHAIN VERTICES============\n");
            for (int vertexIndex = 0; vertexIndex < this.BottomChain.size(); vertexIndex++) {
                writer.write(this.BottomChain.get(vertexIndex) + "\n");
            }
            writer.write("==================EDGES=====================\n");
            for (int edgeIndex = 0; edgeIndex < this.Edges.size(); edgeIndex++) {
                writer.write(this.Edges.get(edgeIndex) + "\n");
            }
            writer.close();
        } catch (IOException ex) {
            System.out.println("¯\\_(ツ)_/¯ \n" + ex.getMessage());
        }
    }


}

    //VISIBLITY SET HELPERS ==================================
    //assuming this starts with 2, as 1 was already computed
    //will be called with i-1, i, and temp

    /*public void Make_top(int j, int k) {
        Vertex upperChild = Vertices.get(j).getUpperChild();
        while (upperChild != null && isAbove(Vertices.get(k), upperChild, Vertices.get(j))) {
            Make_top(Vertices.indexOf(upperChild), k);
            upperChild = upperChild.getSibling();
            //Vertices.get(j).setUpperChild(Vertices.get(j).getUpperChild().getSibling());
        }
        //even if temp is reset, if this line is before the other on the first iteration it gets overwritten.
        tmp.setSibling(Vertices.get(j));    //if tmp is reset every iteration, setting the sibling for the last iteration doesn't matter. what needs to happen here?
        //tmp = Vertices.get(j);

    }*/
    
    /*
    public Make_Vb(int i) {
        //t = tmp;
        //Make_bot(i - 1, i, )
        //this.Vertices.get(i).setLowerChild(tmp.getSibling());
    }
    */


        /*//testing things
        this.Vertices = new ArrayList<Vertex>();
        //thing found on page 282 of generate 1
        //vert 1
        this.Vertices.add(new Vertex(1, 10));
        //vert 2
        this.Vertices.add(new Vertex(3, 6));
        //vert 3
        this.Vertices.add(new Vertex(4, 1));
        //vert 4
        //this.Vertices.add(new Vertex(6, 9));
        //vert 5
        //this.Vertices.add(new Vertex(9, 11));
        tmp = new Vertex(false);
        for (int vertexIndex = 1; vertexIndex < this.Vertices.size(); vertexIndex++) {  
            Make_top(vertexIndex - 1, vertexIndex);
            Vertices.get(vertexIndex).setUpperChild(tmp.getSibling());
            
            tmp = new Vertex(false);
        }
        //should return coordinates for 1, (1, 10)
        //all siblings and upper children are currently null
        System.out.println(Vertices.get(2).getUpperChild());*/