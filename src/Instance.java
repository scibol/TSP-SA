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

    public double getNaiveSolution(int startSeed) {
        int[] visitedNodes = new int[130];
        visitedNodes[0] = startSeed;
        int r = startSeed;
        double pathLength = 0;
        
        for (int i = 1; i < this.dimension; i++) {
            double min = Double.POSITIVE_INFINITY;
            int newNode = 0;
;            for (int j = 0; j < this.dimension; j++) {
                if (this.distanceMap[r][j] < min && r != j && !isVisited(visitedNodes, j, i)) {
                    min = this.distanceMap[r][j];
                    newNode = j;
                }
            }
            visitedNodes[i] = newNode;
            pathLength += min;
            r = newNode;
        }
        pathLength += this.distanceMap[r][startSeed];
        return pathLength;
    }

    public void getBestSolution() {
        double minPath = Double.POSITIVE_INFINITY;
        int index=0;
        for (int i = dimension - 1; i >= 0; i--) {
//            Random n = new Random();
//            int r = n.nextInt(i + 1);
            double currentMin = getNaiveSolution(i);
            if (currentMin < minPath) {
                index = i;
                minPath = currentMin;
            }
        }
        System.out.println(index);
        System.out.println(minPath);
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