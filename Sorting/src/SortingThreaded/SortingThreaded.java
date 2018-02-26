import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Michael Sloma on 9/11/2017.
 */
public class SortingThreaded {
    static Object[] insertionSort(int[] arr){
        int swaps = 0;
        long startTime = System.nanoTime();
        long endTime;
        long timeDiff;

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
        endTime = System.nanoTime();
        timeDiff = (endTime - startTime) / (long)1000000000.0;

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
        int[] tmpArr = fileToArray(inFile);

        //Object[] tuple;
        //Thread sort = new Thread(){
        //    @Override
        //    public void run(){
        //        tuple = insertionSort(tmpArr);
        //    }
        //};
//
        //int[] sortedArr = (int[])tuple[0];
//
        //System.out.println("Swaps: " + (int)tuple[1]);
        //System.out.println("Runtime: " + (long)tuple[2]);
//
        for(int i = 0; i < 30; i++){
            //System.out.println(sortedArr[i]);
        }

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

