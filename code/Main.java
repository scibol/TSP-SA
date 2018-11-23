import java.io.File;
import java.util.Random;

public class Main {
    public static void usage() {
        System.out.println("java Main <file> <seed>");
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            usage();
            System.exit(-1);
        }
        File f = new File(args[0]);
        long seed = Long.parseLong(args[1]);
        if (!f.exists() || f.isDirectory()) {
            System.out.println("File " + args[0] + " does not exist!");
            usage();
            System.exit(-1);
        }
        Instance instance = new Instance(args[0]);
        try {
            instance.read();
                Random random = new Random(seed);
                Tour NN = new Tour();
                NN.generateIndividual();

                boolean nearest = random.nextBoolean();
                if (nearest) {
                    NN = NearestNeighbor.computeSolution(random);
                } else {
                    NN = new Tour();
                    NN.generateIndividual();
                }
                TwoOpt.optimise(NN);

                Tour SA = SimulatedAnnealing.computeSolution(NN, random);
                int cost = SA.getDistance();
                System.out.println(SA);
                System.out.println(cost);

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-2);
        }
    }
}
