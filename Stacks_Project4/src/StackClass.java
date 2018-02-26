import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

public class StackClass {
    private static Stack<String> words;
    private static void readFile(File inFile) throws IOException{
        words = new Stack<>();
        String line;

        FileReader inReader = new FileReader(inFile);
        BufferedReader inReadBuff = new BufferedReader(inReader);

        while ((line = inReadBuff.readLine()) != null) {
            if(line.equals("DONE")) break;
            else {
                words.push(line);
            }
        }
    }

    private static String printStack(){
        String output = "";
        int size = words.size();
        for (int i = 0; i < size; i++) {
            output += words.pop() + "\n";
        }
        return output;
    }

    public static void main(String[] args) throws IOException{
        readFile(new File("src/word_list.txt"));
        System.out.println(printStack());
    }
}
