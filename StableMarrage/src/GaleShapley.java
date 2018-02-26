import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import LinkedList.LinkedList;

class tooManyPeople extends Exception{
    Exception tooManyPeopleException(){
        System.out.println("There are too many people in your preferences list!");
        return null;
    }
}

public class GaleShapley {
    private static HashMap<String, LinkedList<String>> men;
    private static HashMap<String, LinkedList<String>> women;

    /**
     * I spoke with Dr.Ledgard and he said I was allowed to use HashMaps and ArrayLists
     * as I understand what they are and how to use them.
     */

    public static String outputFile(String fileName) throws Exception{
        File inFile = new File(fileName);
        FileReader inReader = new FileReader(inFile);
        BufferedReader inReadBuff = new BufferedReader(inReader);
        String line;
        String output = "";
        while ((line = inReadBuff.readLine()) != null) {
            output += line + "\n";
        }
        return output;

    }
    private static LinkedList<String> initializeLinkedList(String line, int numOfMatches){
        LinkedList<String> ranking = new LinkedList<String>();
        int lastSpace = line.indexOf(":") + 2;

        for(int i = 0; i < numOfMatches; i++){
            if(lastSpace > line.length()){
                lastSpace = line.length();
            }
            else {
                int currSpace = line.indexOf(" ", lastSpace);

                if(currSpace != -1) {
                    ranking.add(line.substring(lastSpace, currSpace));
                    lastSpace = currSpace + 1;
                }
                else{
                    currSpace = line.length();
                    ranking.add(line.substring(lastSpace, currSpace));
                    lastSpace = currSpace + 1;
                }
            }
        }
        return ranking;
    }

    private static void readFile(File inFile) throws Exception{
        String key;
        LinkedList<String> ranking;
        String line;
        int lineNum       = 0;
        int numOfMatches  = 0;
        int peopleCounter = 0;

        FileReader inReader = new FileReader(inFile);
        BufferedReader inReadBuff = new BufferedReader(inReader);

        while ((line = inReadBuff.readLine()) != null) {
            if (lineNum == 0) {
                try {
                    numOfMatches = Integer.parseInt(line);
                } catch (NumberFormatException e) {
                    System.out.println("Number of people is not an integer! Line: 0");
                }
            } else if (!line.contains("//") && line.contains(":")) {
                key = line.substring(3, line.indexOf(":"));
                ranking = initializeLinkedList(line, numOfMatches);

                if (peopleCounter < numOfMatches) {
                    men.put(key, ranking);
                }
                else if(peopleCounter > numOfMatches * 2) {
                    throw new tooManyPeople().tooManyPeopleException();
                }
                else{
                    women.put(key, ranking);
                }
                peopleCounter++;
            }
            lineNum++;
        }
    }

    private static HashMap<String, String> galeShapley(){
        ArrayList<String> menFree       = new ArrayList<>();
        ArrayList<String> womenFree     = new ArrayList<>();
        HashMap<String, String> matches = new HashMap<>();

        menFree.addAll(men.keySet());
        womenFree.addAll(women.keySet());

        while (!menFree.isEmpty()) {
            String man = menFree.get(0);

            for (int j = 0; j < women.size(); j++) {
                String woman = men.get(man).get(j);

                if (womenFree.contains(woman)) {
                    matches.put(woman, man);
                    menFree.remove(0);
                    womenFree.remove(woman);
                    break;
                }
                else if (matches.containsKey(woman)) {
                    int currMatch = 0;
                    int newMan    = 0;
                    LinkedList<String> womansPref = women.get(woman);
                    String womansCurrMatch = matches.get(woman);

                    for (int i = 0; i < womansPref.size(); i++) {
                        if (womansPref.get(i).equals(man)) {
                            newMan = i;
                        } else if (womansPref.get(i).equals(womansCurrMatch)) {
                            currMatch = i;
                        }
                    }

                    if (currMatch > newMan) {
                        String oldMan = matches.get(woman);
                        menFree.remove(0);
                        menFree.add(oldMan);
                        matches.replace(woman, man);
                        break;
                    }
                }
            }
        }
        return matches;
    }

    private static String toFormatting(HashMap<String, String> matches){
        String outputFormatted = "";
        for (String s: matches.keySet())
        {
            outputFormatted += (s + " = " + matches.get(s) + "\n");
        }
        return outputFormatted;
    }

    public static String runGaleShapley(String fileName) throws Exception{
        men = new HashMap<>();
        women = new HashMap<>();
        HashMap<String, String> matches;

        readFile(new File(fileName));
        matches = galeShapley();
        return toFormatting(matches);
    }
}
