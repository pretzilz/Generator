//this class represents temp. we need this to be separate because of silly pointer logic that i'm having trouble implementing in java.
package PolygonGenerator;
public class TempHelper {
    public Vertex ptr;
    public Vertex upperChild;
    public Vertex lowerChild;
    public Vertex sibling;

    public TempHelper() {
        ptr = null;
        upperChild = null;
        lowerChild = null;
        sibling = null;
    }

}