public class TwoOpt {

    public static void optimise(Tour tour) {
        double bestGain;
        double localGain;
        int first = -1, second = -1;
        City a, b, c, d;
        int dimension = tour.tourSize();
        boolean repeat = true;
        while (repeat) {
            repeat = false;
            for (int i = 0; i < dimension; i++) {
                bestGain = 0;
                for (int j = i + 2; j < dimension; j++) {

                    a = tour.getCity(i);
                    b = tour.getCity(i + 1);
                    c = tour.getCity(j);
                    d = tour.getCity((j + 1) % dimension);

                    localGain = moveCost(a, b, c, d);
                    if (localGain == 1) {
                        continue;
                    }

                    if (localGain < bestGain) {
                        first = i;
                        second = j;
                        bestGain = localGain;
                    }
                }
                if (bestGain < 0) {
                    repeat = true;
                    swap(first + 1, second, tour);
                }
            }
        }
    }

    private static double moveCost(City a, City b,
                            City c, City d) {
        double _ab = a.distanceTo(b), _cd = c.distanceTo(d);
        double _ac = a.distanceTo(c), _bd = b.distanceTo(d);
        if(_ab < _ac && _cd < _bd)
            return 1;
        return (Maths.sqrt(_ac) + Maths.sqrt(_bd)) -
                (Maths.sqrt(_ab) + Maths.sqrt(_cd));
    }

    private static void swap(int i, int j, Tour tour) {
        City city1, city2;
        while (i < j) {
            city1 = tour.getCity(i);
            city2 = tour.getCity(j);
            tour.setCity(i, city2);
            tour.setCity(j, city1);
            i++;
            j--;
        }
    }

}
