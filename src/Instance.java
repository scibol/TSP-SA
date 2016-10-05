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
            for (int j = 0; j < d; j++) {
                this.distanceMap[i][j] = getDistance(this.nodeList.get(i), this.nodeList.get(j));
            }
        }
    }

    public boolean isVisited(int[] visitedNodes, int node, int index) {
        for (int i = 0; i < index; i++) {
            if (visitedNodes[i] == node) {
                return true;
            }
        }
        return false;
    }

    public void getNaiveSolution() {
        int d = this.dimension;
//        int r = n.nextInt(d+1);
        int r = 4;
        int[] visitedNodes = new int[130];
        visitedNodes[0] = r;
        double pathLength = 0;
        int start = r;
        int i = 1;
        while (i < d) {
            int j = 0;
            double min = 100000000.0;
            int newNode = 0;
            while (j < d) {
                if (this.distanceMap[r][j] < min && r != j && !isVisited(visitedNodes, j, i)) {
                    min = this.distanceMap[r][j];
                    newNode = j;
                }
                j++;
            }
            visitedNodes[i] = newNode;
            pathLength += min;
            r = newNode;
            i++;
        }
        pathLength += this.distanceMap[r][start];
        System.out.println(pathLength);
        printSolution(visitedNodes);
    }

    public void printSolution(int[] visitedNodes) {
        for (int i = 0; i < visitedNodes.length; i++) {
            System.out.println(visitedNodes[i] + 1);
        }
    }

    public double getDistance(Node i, Node j) {
        return Math.round(Math.sqrt(Math.pow(i.x - j.x, 2) + Math.pow(i.y - j.y, 2)));
    }
}