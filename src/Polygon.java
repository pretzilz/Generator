package PolygonGenerator;
import java.util.*;
import java.io.*;
public class Polygon {
    //TODO fix case where bottom chain can intersect with itself - still an issue?
    //TODO make y & x point generation some kind of function?
    //TODO fix bottom chain x value going very high?
    //TODO fix bottom chain having several of the same vertices
    public ArrayList<Vertex> Vertices;
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

    private static final int MAX_ITERATIONS = 5000; //to break the loop in case it gets stuck.
    private int iterations;
    /**
     * Generates a polygon. Attempts to make one in MAX_ITERATIONS iterations, and if it can't, it starts over.
     * This is horrible, but you know, whatever.
     */
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
        iterations = 0;
        while (true) {
            generateTopChain();                        
            generateBottomChain();
            if (iterations == MAX_ITERATIONS) { //try again
                iterations = 0;
                this.TopChain = new ArrayList<Vertex>();
                this.BottomChain = new ArrayList<Vertex>();
                this.Edges = new ArrayList<Edge>();
                this.largestX = Integer.MIN_VALUE;
                this.largestY = Integer.MIN_VALUE;
                this.smallestX = Integer.MAX_VALUE;
                this.smallestY = Integer.MAX_VALUE;
            }
            else {
                break;
            }
        }
        this.width = this.largestX - this.smallestX;
        this.height = this.largestY - this.smallestY;
    }

    /**
     * Generates the top chain of the polygon. 
     * 
     */
    private void generateTopChain() {
        int xStart = 0;
        Random rand = new Random();
        k = (2*numVertices/10) + rand.nextInt(((8*numVertices)/10) - (2*numVertices/10) + 1); //currently ensures that it generates something hopefully not the boundaries
        //compute the top chain
        for(int topChainIndex = 0; topChainIndex < k; topChainIndex++){
            //x coordinate is some random "normally distributed" amount to the right of the previous one
            int xVal = (int)Math.abs(Math.round(40 * rand.nextGaussian()) + 1);
            int yVal = (int)Math.abs(Math.round(150 * rand.nextGaussian()));
            Vertex newVertex = new Vertex(xStart + xVal, yVal);
            xStart += xVal;
            Vertices.add(newVertex);
            TopChain.add(newVertex);
            
            largestX = Math.max(largestX, xStart);
            smallestX = Math.min(smallestX, xStart);
            largestY = Math.max(largestY, yVal);
            smallestY = Math.min(smallestY, yVal);
            if (topChainIndex > 0) {
                Edge newEdge = new Edge(TopChain.get(topChainIndex - 1), TopChain.get(topChainIndex), true);
                Edges.add(newEdge);
            }
        }
    }

    /**
     * Generates the bottom chain of the polygon. 
     * Attempts to randomly generate a point, then checks to see if it intersects with something.
     * If not, it adds it to the bottom chain. 
     * The first vertex of the bottom chain is connected with the first vertex of the top chain, 
     * and the last vertex of the bottom chain is connected with the last vertex of the first chain.
     * If it does intersect, it tries again.
     */
    private void generateBottomChain() {
        int xStart = 0;
        Random rand = new Random();
        for(int bottomChainIndex = 0; bottomChainIndex < numVertices-k && iterations < MAX_ITERATIONS; bottomChainIndex++){
            //x coordinate is some random, "normally distributed" amount to the right of the previous one
            int xVal = (int)Math.abs(Math.round(40 * rand.nextGaussian()) + 1);
            int yVal = (int)Math.abs(Math.round(150 * rand.nextGaussian()));
            Vertex newVertex = new Vertex(xStart + xVal, yVal);
            Vertex otherVertexOfLine = new Vertex();
            if (bottomChainIndex == 0) {  //if we're at the first vertex of the bottom chain, it will be connected to the first vertex of the top chain.
                if(isAbove(newVertex, this.TopChain.get(0), this.TopChain.get(1))) {    //if the first vertex is above the first line, it won't work anyway.
                    bottomChainIndex--;
                    iterations++;
                    continue;
                }
                else {  //if it's the first vertex and it's below the first line, it should be good but it might intersect with a different edge in the top chain
                    otherVertexOfLine = this.TopChain.get(0);
                    if (!intersectsWithNotFirstEdgeTopChain(otherVertexOfLine, newVertex)) {
                        addToBottomChain(newVertex, otherVertexOfLine, xStart, yVal);
                        xStart += xVal;
                        iterations = 0;
                    } else {
                        bottomChainIndex--;
                        iterations++;
                        continue;
                    }
                }
            }
            else {
                otherVertexOfLine = this.BottomChain.get(bottomChainIndex - 1); //otherwise the other point is the last point we added in the bottom chain
            }

            if (bottomChainIndex != 0) {
                 if (!intersectsWithTopChain(otherVertexOfLine, newVertex)){
                    if (bottomChainIndex == numVertices-k-1) {   //if it's the last vertex, add the edge between the last vertex on bottom chain and the last vertex of the top chain
                        if (!intersectsWithNotLastEdgeTopChain(newVertex, this.TopChain.get(k-1)) && !intersectsWithBottomChain(newVertex, this.TopChain.get(k-1))) {
                            addToBottomChain(newVertex, otherVertexOfLine, xStart, yVal);
                            xStart += xVal;
                            Edge lastEdge = new Edge(newVertex, this.TopChain.get(k-1), false);
                            Edges.add(lastEdge);
                            iterations = 0;
                        } else {
                            bottomChainIndex--;
                            iterations++;
                            continue;
                        }
                        
                    }
                    else {
                        if (!intersectsWithBottomChain(otherVertexOfLine, newVertex)) {
                            addToBottomChain(newVertex, otherVertexOfLine, xStart, yVal);
                            xStart += xVal;
                            iterations = 0;
                        }
                        else {
                            bottomChainIndex--;
                            iterations++;
                            continue;
                        }
                        
                    }
                }
                else {
                    bottomChainIndex--;
                    iterations++;
                    continue;
                }
            }
        }
    }


    public void addToBottomChain(Vertex newVertex, Vertex otherVertexOfLine, int x, int y) {
        Vertices.add(newVertex);
        BottomChain.add(newVertex);
        largestX = Math.max(largestX, x);
        smallestX = Math.min(smallestX, x);
        largestY = Math.max(largestY, y);
        smallestY = Math.min(smallestY, y);
        Edge newEdge = new Edge(otherVertexOfLine, newVertex, false);
        Edges.add(newEdge);
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

    /**
     * This determines whether or not a vertex k is above the line (j1, j2)
     */
    public boolean isAbove(Vertex k, Vertex j1, Vertex j2) {
        if (j2.getX() - j1.getX() == 0) {   //if, somehow, there was a straight line, it's not monotone. try again.
            return false;
        }
        double slope = ((j2.getY() - j1.getY())/(j2.getX() - j1.getX()));   //get slope
        double yInt = -((slope * j1.getX()) - j1.getY());   //find y intercept
        double yCoordOfKOnLine = (slope * k.getX()) + yInt;    //get the y coordinate of k on line
        double yDiff = yCoordOfKOnLine - k.getY(); //get difference between actual y coordinate of k and the one on the line
        //if it's greater than 0, it's below the line. if it's equal, then it's on the line.
        return yDiff < 0; 

    }

    /**
     * Check to see if a line created by two vertices intersects with any edge in the top chain.
     */
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

    /**
     * Check to see if a line created by two vertices intersects with any edge in the top chain, except for the first.
     * Used for adding the first vertex of the bottom chain, because it will always theoretically intersect with the top chain, as they're connected
     */
    public boolean intersectsWithNotFirstEdgeTopChain(Vertex v1, Vertex v2) {   //figure out if this line intersects with any line in the top chain save for the first
        Edge newEdge = new Edge(v1, v2, false);
        for (int topEdgeIndex = 2; topEdgeIndex < this.TopChain.size(); topEdgeIndex++) {
            Edge currentTopEdge = new Edge(this.TopChain.get(topEdgeIndex - 1), this.TopChain.get(topEdgeIndex), false);
            if (newEdge.intersects(currentTopEdge)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check to see if a line created by two vertices intersects with any edge in the top chain, except for the last.
     * Used for adding the last vertex of the bottom chain, because it will always theoretically intersect with the top chain, as they're connected
     */
    public boolean intersectsWithNotLastEdgeTopChain(Vertex v1, Vertex v2) {   //figure out if this line intersects with any line in the top chain save for the last
        Edge newEdge = new Edge(v1, v2, false);
        for (int topEdgeIndex = 1; topEdgeIndex < this.TopChain.size() - 1; topEdgeIndex++) {
            Edge currentTopEdge = new Edge(this.TopChain.get(topEdgeIndex - 1), this.TopChain.get(topEdgeIndex), false);
            if (newEdge.intersects(currentTopEdge)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if the new edge intersects with the edges already generated in the bottom chain. 
     * This will only happen making the last edge connecting to the last point in the top chain.
     */
    public boolean intersectsWithBottomChain(Vertex v1, Vertex v2) {
        Edge newEdge = new Edge(v1, v2, false);
        for (int bottomEdgeIndex = 1; bottomEdgeIndex < this.BottomChain.size(); bottomEdgeIndex++) {
            Edge currentBottomEdge = new Edge(this.BottomChain.get(bottomEdgeIndex - 1), this.BottomChain.get(bottomEdgeIndex), false);
            if (newEdge.intersects(currentBottomEdge)) {
                return true;
            }
        }
        Edge firstEdge = new Edge(this.TopChain.get(0), this.BottomChain.get(0), false);   //also check if it intersects with the first edge of the bottom chain, which wasn't included
        if (newEdge.intersects(firstEdge)) {
            return true;
        }
        return false;
    }

    /**
     * Prints data about the generated polygon to a file.
     */
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