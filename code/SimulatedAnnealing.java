import java.util.Random;

public class SimulatedAnnealing {

    public static double annealing(double energy, double newEnergy, double temperature) {
        if (newEnergy < energy)
            return 1.0;
        else
            return Math.exp((energy - newEnergy) / temperature);
    }

    public static Tour computeSolution(Tour initial, Random random) {
        double temp = 10000;
        double coolingRate = 0.97;

        Tour best = new Tour(initial.getTour());
        int size = initial.tourSize();
        Tour current = new Tour(initial.getTour());

        while (temp > 1) {
            Tour next = new Tour(current.getTour());
            int tourPos1 = random.nextInt(size);
            int tourPos2 = random.nextInt(size);
            if (tourPos1 != tourPos2) {
                City citySwap1 = next.getCity(tourPos1);
                City citySwap2 = next.getCity(tourPos2);

                next.setCity(tourPos2, citySwap1);
                next.setCity(tourPos1, citySwap2);
            }

            TwoOpt.optimise(next);
            double currentEnergy = best.getDistance();
            double nextEnergy = next.getDistance();

            if (annealing(currentEnergy, nextEnergy, temp) > random.nextDouble()) {
                current = new Tour(next.getTour());
            }

            if (current.getDistance() < best.getDistance()) {
                best = new Tour(current.getTour());
            }
            temp *= coolingRate;
        }

        return best;
    }
}
