import java.util.Random;

public class NearestNeighbor {

    private static long[][] distanceMap;

    public static void createDistanceMap(Tour currentSolution) {
        int d = currentSolution.tourSize();
        distanceMap = new long[d][d];
        for (int i = 0; i < d; i++) {
            City first = currentSolution.getCity(i);
            for (int j = 0; j < d; j++) {
                City second = currentSolution.getCity(j);
                distanceMap[i][j] = (long) first.distanceTo(second);
            }
        }
    }

    public static boolean isVisited(int[] visitedNodes, int node, int index) {
        for (int i = 0; i < index; i++) {
            if (visitedNodes[i] == node) {
                return true;
            }
        }
        return false;
    }

    public static Tour computeSolution(Random random) {
        Tour currentSolution = new Tour();
        currentSolution.generateIndividual();
        createDistanceMap(currentSolution);

        Tour newSolution = new Tour(currentSolution.getTour());

        int d = currentSolution.tourSize();
        int[] visitedNodes = new int[d];

        int r = random.nextInt(d);

        City startCity = currentSolution.getCity(r);
        newSolution.setCity(0, startCity);
        visitedNodes[0] = r;

        for (int i = 1; i < d; i++) {
            double min = Double.POSITIVE_INFINITY;
            int cityIndex = 0;
            for (int j = 0; j < d; j++) {
                if (distanceMap[r][j] < min && r != j && !isVisited(visitedNodes, j, i)) {
                    min = distanceMap[r][j];
                    cityIndex = j;
                }
            }
            visitedNodes[i] = cityIndex;
            City city = currentSolution.getCity(cityIndex);
            newSolution.setCity(i, city);
            r = cityIndex;
        }

        return newSolution;
    }
}
