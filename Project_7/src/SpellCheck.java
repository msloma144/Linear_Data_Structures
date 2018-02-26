import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class SpellCheck {
    private Dictionary dictionary;
    private HashMap<String, ArrayList<String>> suggestions;
    private String suggList;

    protected SpellCheck(String dictionary, String suggestions, String delineator) throws IOException{
        this.dictionary = new Dictionary(dictionary, delineator);
        this.suggestions = new HashMap<>();
        this.suggList = "";
        buildSuggestionList(suggestions);
    }

    private void buildSuggestionList(String suggestions) throws IOException{
        FileReader inReader = new FileReader(new File(suggestions));
        BufferedReader inReadBuff = new BufferedReader(inReader);
        String line;

        while ((line = inReadBuff.readLine()) != null) {
            String misspelled = line.substring(0, line.indexOf("-"));
            ArrayList<String> lookup = new ArrayList<>();
            line = line.substring(line.indexOf(">") + 1);

            while (line.length() != 0){
                if(!line.contains(",")){
                    lookup.add(line);
                    line = "";
                }
                else{
                    lookup.add(line.substring(0, line.indexOf(",")));
                    line = line.substring(line.indexOf(",") + 2);
                }
            }

            this.suggestions.put(misspelled, lookup);
        }
    }

    private void suggest(String word){
        if(suggestions.containsKey(word)) {
            this.suggList += word + ":\n";
            for (String sugg : suggestions.get(word)) {
                suggList += "   " + sugg + "\n";
            }
            suggList += "\n";
        }
    }

    private String checkWord(String word){
        String wordCleaned = word.replaceAll("[^a-zA-Z]", "").toLowerCase();
        if(dictionary.contains(wordCleaned)){
            return word;
        }
        else {
            suggest(word);
            return "*" + word + "*";
        }
    }

    public String readDocument(String file) throws IOException{
        FileReader inReader = new FileReader(new File(file));
        BufferedReader inReadBuff = new BufferedReader(inReader);
        String line;
        String output = "";
        this.suggList = "";

        while ((line = inReadBuff.readLine()) != null){
            while (line.length() != 0){
                if(!line.contains(" ")){
                    int lastChar = line.toLowerCase().charAt(line.length() - 1);
                    if(lastChar >= 48 && lastChar <= 122){
                        String word = line.substring(0, line.length());
                        word = checkWord(word);
                        output += word + "\n";
                        line = "";
                    }
                    else{
                        String word = line.substring(0, line.length() - 1);
                        word = checkWord(word);
                        output += word + line.substring(line.length() - 1, line.length()) + "\n";
                        line = "";
                    }
                }
                else {
                    String word = line.substring(0, line.indexOf(" "));
                    word = checkWord(word);
                    output += word + " ";
                    line = line.substring(line.indexOf(" ") + 1);
                }
            }
        }
        return output;
    }

    private String removeStars(String word){
        return word.substring(1, word.length() -1);
    }

    public String readInput(String text) throws IOException{
        BufferedReader inReadBuff = new BufferedReader(new StringReader(text));

        String line;
        String output = "";
        this.suggList = "";

        while ((line = inReadBuff.readLine()) != null){
            while (line.length() != 0){
                if(!line.contains(" ")){
                    int lastChar = line.toLowerCase().charAt(line.length() - 1);
                    //check if stars are present already
                    if(line.charAt(0) == 42 && lastChar == 42){
                        String word = removeStars(line);
                        word = checkWord(word);
                        output += word + "\n";
                        line = "";
                    }
                    //check if last char is a letter
                    else if(lastChar >= 48 && lastChar <= 122 && lastChar != 63){
                        String word = line.substring(0, line.length());
                        word = checkWord(word);
                        output += word + "\n";
                        line = "";
                    }
                    else{
                        String word = line.substring(0, line.length() - 1);

                        if(word.charAt(0) == 42 && word.charAt(word.length() - 1) == 42){
                            word = removeStars(word);
                            word = checkWord(word);
                            output += word + line.substring(line.length() - 1, line.length()) + "\n";
                            line = "";
                        }
                        else {
                            word = checkWord(word);
                            output += word + line.substring(line.length() - 1, line.length()) + "\n";
                            line = "";
                        }
                    }
                }
                else if(line.contains(" ") && line.charAt(0) == ' '){
                    output += " ";
                    line = line.substring(line.indexOf(" ") + 1);
                }
                else {
                    String word = line.substring(0, line.indexOf(" "));

                    if(word.charAt(0) == 42 && word.charAt(word.length() - 1) == 42){
                        word = removeStars(word);
                    }

                    word = checkWord(word);
                    output += word + " ";
                    line = line.substring(line.indexOf(" ") + 1);
                }
            }
        }
        return output;
    }

    public String getSuggList() {
        return suggList;
    }
}
