import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * TODO: Missing parts
 */
public class Instance {

    private String filename;
    private int dimension;
    private double[][] matrix;


    private class Node {
        public double x;

        public double y;

        public Node(double x, double y) {
            this.x = x;
            this.y = y;
        }
        @Override
        public String toString() {
            return "Node{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }


    public int size() {
        return dimension;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Instance(String filename) {

        this.filename = filename;
        System.out.println(this.filename);
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    // TODO: Read the file
    public void read() throws Exception {
        FileReader f = new FileReader(this.filename);
        BufferedReader bufRead = new BufferedReader(f);
        String myLine = null;
        while ( (myLine = bufRead.readLine()) != null) {
            if (Character.isDigit(myLine.charAt(0))){
                String[] rawNode = myLine.split(" ");
                Node n = new Node(Double.parseDouble(rawNode[1]), Double.parseDouble(rawNode[2]));
            }
        }
    }

    // TODO: Get the distance between i and j from the distance map
    public long getDistance(int i, int j) {
        return -1;
    }

    // TODO: Calculate distance between 2 nodes
    private long distance(Node n1, Node n2) {
        return -1;
    }

    // TODO: Given the graph compute the distance map
    private void createDistanceMap() {
    }

}
