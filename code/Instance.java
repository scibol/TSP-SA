import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Created by lucascibona on 02/11/16.
 */
public class Instance {

    private String filename;

    public Instance(String filename) {
        this.filename = filename;
    }

    public void read() throws Exception {
        FileReader f = new FileReader(this.filename);
        BufferedReader bufRead = new BufferedReader(f);
        String myLine = null;
        while ((myLine = bufRead.readLine()) != null) {
            if (Character.isDigit(myLine.trim().charAt(0))) {
                String[] rawNode = myLine.trim().split(" ");
                City n = new City(Math.round(Double.parseDouble(rawNode[1])), Math.round(Double.parseDouble(rawNode[2])), Integer.parseInt(rawNode[0]));
                TourManager.addCity(n);
            }
        }
    }
}
