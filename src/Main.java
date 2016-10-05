import java.io.File;

public class Main {
    public static void usage() {
        System.out.println("java Main <file>");
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            usage();
            System.exit(-1);
        }
        File f = new File(args[0]);
        if (!f.exists() || f.isDirectory()) {
            System.out.println("File " + args[0] + " does not exist!");
            usage();
            System.exit(-1);
        } else {
            System.out.println("file opened");
        }

        Instance instance = new Instance(args[0]);
        try {
            instance.read();
            instance.createDistanceMatrix();
            instance.getNaiveSolution();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-2);
        }

        // TODO: Return naïve solution and its objective value
    }

}
