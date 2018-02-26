import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Michael Sloma on 9/11/2017.
 */
public class Sorting {
    static Object[] insertionSort(int[] arr){
        int swaps = 0;
        double startTime = System.currentTimeMillis();
        double endTime;
        double timeDiff;

        for(int i = 1; i < arr.length; i++){
            if (arr[i] < arr[i - 1]){
                for(int j = i; j > 0; j--) {
                    if (arr[j] < arr[j - 1]) {
                        int temp = arr[j]; arr[j] = arr[j - 1]; arr[j - 1] = temp;
                        swaps++;
                    }
                }
            }
        }
        endTime = System.currentTimeMillis();
        timeDiff = (endTime - startTime)/1000.;

        return new Object[]{arr, swaps, timeDiff};
    }

    static int[] fileToArray(File inFile) throws IOException {
        int[] outArr = new int[50000];
        String line;
        int i = 0;

        FileReader inReader = new FileReader(inFile);
        BufferedReader inReadBuff = new BufferedReader(inReader);

        while ((line = inReadBuff.readLine()) != null) {
            try {
                outArr[i] = Integer.parseInt(line);
            }
            catch (java.lang.NumberFormatException e){
                System.out.println("The number entered is not an integer!");
            }
            i++;
        }
        return outArr;
    }

    static void outputFileData(File inFile) throws IOException{
        System.out.println("File Name: " + inFile.getName());
        int[] sortedArr;
        int[] tmpArr = fileToArray(inFile);
        Object[] tuple;

        for(int i = 0; i < 30; i++){
            System.out.println(tmpArr[i]);
        }

        tuple = insertionSort(tmpArr);

        System.out.println("Swaps: " + (int)tuple[1]);
        System.out.println("Runtime: " + (Double)tuple[2]);

        sortedArr = (int[])tuple[0];


        
    }

    public static void main(String[] args) throws IOException{
        File forward = new File("./src/Sorting/forward50000.txt");
        File reverse = new File("./src/Sorting/reverse50000.txt");
        File random = new File("./src/Sorting/random50000.txt");

        outputFileData(forward);
        outputFileData(reverse);
        outputFileData(random);
    }
}
