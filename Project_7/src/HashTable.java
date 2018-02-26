import java.util.Arrays;

/**
 * Created by Michael Sloma on 11/28/2017.
 */
public class HashTable {
    private String[] contents;
    private int inserted;
    private int size;
    private int remaps;

    public HashTable(){
        this.size = 1000;
        this.contents = new String[this.size];
        this.inserted = 0;
        this.remaps = 0;
    }

    public HashTable(int size){
        this.size = size;
        this.contents = new String[size];
        this.inserted = 0;
        this.remaps = 0;
    }

    private boolean isPrime(int n) {
        if (n % 2 == 0)
            return false;
        else
            for(int i = 3; i * i <= n; i += 2) {
                if(n % i == 0)
                    return false;
            }
        return true;
    }


    private void doubleTable(){
        String[] oldContents = this.contents;
        int newTableSize = size * 2;
        while (!isPrime(newTableSize)){
            newTableSize++;
        }
        this.contents = new String[newTableSize];
        this.size = contents.length;
        this.inserted = 0;

        for(String word: oldContents){
            if(word != null)
                insert(word);
        }

        this.remaps = 0;
    }

    private int probe(int hashIndex){
        int i = hashIndex;
        int counter = 0;

        do{
            if(contents[i] == null){
                this.remaps += counter;
                return i;
            }
            counter++;
            i = (i + 1) % contents.length;
        }while (i != hashIndex);

        return Integer.MAX_VALUE;
    }

    private int hash(String word){
        String lowerWord = word.toLowerCase();
        char[] wordParts = lowerWord.toCharArray();
        int hashVal = 0;
        int numChunks;

        if(wordParts.length % 4 == 0)
            numChunks = wordParts.length / 4;
        else numChunks = wordParts.length / 4 + 1;


        for(int i = 0; i < numChunks; i++){
            int[] tmp = new int[4];

            for(int j = 0; j < 4; j++) {
                if(j >= wordParts.length){
                    tmp[j] = 0;
                }
                else {
                    int charVal = Character.getNumericValue(wordParts[j]) - 9;
                    tmp[j] = charVal;
                }
            }
            if(wordParts.length > 4){
                wordParts = Arrays.copyOfRange(wordParts, 4, wordParts.length);
            }
            hashVal += Math.pow(31, 3) * tmp[0] +
                    Math.pow(31, 2) * tmp[1] +
                    Math.pow(31, 1) * tmp[2] +
                    Math.pow(31, 0) * tmp[3];
        }

        return hashVal;
    }

    public void insert(String word){
        int hashVal = hash(word);

        if(((double)inserted / (double)size) >= (1.0/3.0))
        {
            doubleTable();
        }

        contents[probe(hashVal % this.size)] = word;
        this.inserted++;
    }

    public boolean contains(String s){
        int hashVal = hash(s);
        int hashIndex = hashVal % this.size;
        int i = hashIndex;

        do{
            if(contents[i] == null){
                return false;
            }
            else if(contents[i].equalsIgnoreCase(s)){
                return true;
            }
            else
            i = (i + 1) % contents.length;
        }while (i != hashIndex);
        return false;
    }

    public int count(){
        return this.inserted;
    }

    public int getRemaps() {
        return remaps;
    }

    public int getLargestCluster(){
        int largest = 0;
        int counter = 0;
        for (String cont : this.contents){
            if(cont == null){
                if(counter > largest)
                    largest = counter;
                counter = 0;
            }
            else
                counter++;
        }
        return largest;
    }
}
