import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;


public class Instance {

    private String filename;
    private int dimension;
    private ArrayList<Node> nodeList = new ArrayList<>();
    private double distanceMap[][];


    private class Node {
        public long x;
        public int n;
        public long y;

        public Node(int n, long x, long y) {
            this.n = n;
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Node " +
                    +n +
                    ": x=" + x +
                    ", y=" + y;
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
        int i = 0;
        while ((myLine = bufRead.readLine()) != null) {
            if (Character.isDigit(myLine.charAt(0))) {
                i++;
                String[] rawNode = myLine.split(" ");
                Node n = new Node(Integer.parseInt(rawNode[0]), Math.round(Double.parseDouble(rawNode[1])), Math.round(Double.parseDouble(rawNode[2])));
                nodeList.add(n);
            }
        }
        this.dimension = i;
    }

    public void createDistanceMatrix() {
        int d = this.dimension;
        this.distanceMap = new double[d][d];
        for (int i = 0; i < d; i++) {
            for (int j = i+1; j < d; j++) {
                this.distanceMap[i][j] = getDistance(this.nodeList.get(i), this.nodeList.get(j));
            }
        }
    }

    public boolean isVisited(int[] visitedNodes, int node) {
        for (int i = 0; i < visitedNodes.length; i++) {
            if (visitedNodes[i] == node) {
                return true;
            }
        }
        return false;
    }


    public void getNaiveSolution() {
        Random n = new Random();
        String path = new String();
        int d = this.dimension;
        int r = n.nextInt(d+1);
        path += r + " ";
        int[] visitedNodes = new int[130];
        double pathLength = 0;
        int start = r;
        int i = 0;
        while (i < d) {
            int j = i;
            int newNode = j;
            double min = this.distanceMap[r][j];
            while (j < d) {
                if (this.distanceMap[r][j] < min && this.distanceMap[r][j] != 0 && !isVisited(visitedNodes, j)) {
                    min = this.distanceMap[r][j];
                    newNode = j;
                }
                j++;
            }
            visitedNodes[i] = newNode;
            pathLength += min;
            path += newNode + " ";
            r = newNode;
            i++;
        }
        pathLength += this.distanceMap[start][r];
        System.out.println(path);
        System.out.println(pathLength);
    }

    public double getDistance(Node i, Node j) {
        return Math.round(Math.sqrt(Math.pow(i.x - j.x, 2) + Math.pow(i.y - j.y, 2)));
    }
}