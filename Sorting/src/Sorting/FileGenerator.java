import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

/**
 * Created by Michael Sloma on 9/11/2017.
 */
public class FileGenerator {
    public static void main(String[] args) throws IOException{
        File forward = new File("./src/Sorting/forward50000.txt");
        File reverse = new File("./src/Sorting/reverse50000.txt");
        File random = new File("./src/Sorting/random50000.txt");

        PrintWriter outForward = new PrintWriter(forward);
        PrintWriter outReverse = new PrintWriter(reverse);
        PrintWriter outRandom = new PrintWriter(random);

        Random rand = new Random();

        for(int i = 1; i <= 50000; i++) {
            outForward.println(i);
        }

        for(int i = 50000; i >= 1; i--) {
            outReverse.println(i);
        }

        for(int i = 1; i <= 50000; i++) {
            outRandom.println(rand.nextInt(50000 - 1) + 1);
        }

        outForward.close();
        outReverse.close();
        outRandom.close();
    }
}
