import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Dictionary {
    private HashTable hashedDic;

    protected Dictionary(String file, String delineator) throws IOException{
        hashedDic = new HashTable(2);
        FileReader inReader = new FileReader(new File(file));
        BufferedReader inReadBuff = new BufferedReader(inReader);

        if(delineator.equals("\\n")) {
            String line;

            while ((line = inReadBuff.readLine()) != null) {
                if (!line.replaceAll("[^a-zA-Z]", "").equals(""))
                    hashedDic.insert(line.replaceAll("[^a-zA-Z]", "").toLowerCase());
            }
        }
        else if(delineator.equals(" ")){
            String line;
            String word = "";
            while ((line = inReadBuff.readLine()) != null){
                while (line.length() != 0){
                    if(!line.contains(" ") || line.length() == 1){
                        word = "";
                        break;
                    }
                    if(!word.equals("")){
                        word += line.substring(0, line.indexOf(" ")).replaceAll(
                                "[^a-zA-Z]", "").toLowerCase();
                        hashedDic.insert(word);
                        word = "";
                        line = line.substring(line.indexOf(" ") + 1);
                    }
                    else {
                        hashedDic.insert(line.substring(0, line.indexOf(" ")).replaceAll(
                                "[^a-zA-Z]", "").toLowerCase());
                        line = line.substring(line.indexOf(" ") + 1);
                    }
                }
                word = line;
            }

            if(!word.equals("")){
                hashedDic.insert(word);
            }
        }
        System.out.println("Remaps: " + getRemaps());
        System.out.println("Largest Cluster: " + getLargestCluster());
    }

    private int getRemaps(){
        return hashedDic.getRemaps();
    }

    private int getLargestCluster(){
        return hashedDic.getLargestCluster();
    }

    public boolean contains(String word){
        return hashedDic.contains(word);
    }
}
