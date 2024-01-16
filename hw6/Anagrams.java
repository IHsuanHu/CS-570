
// I-Hsuan Hu
//cwid 20010728
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class Anagrams {
    final Integer[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101};
    Map<Character, Integer> letterTable;
    Map<Long, ArrayList<String>> anagramTable;
    
    /**
     * Constructor for the Anagrams class 
     */
    public Anagrams(){
        buildLetterTable();
        anagramTable = new HashMap<>();
    }
    /**
     * Builds the letterTable by mapping each letter to a prime number
     */
    private void buildLetterTable(){
        letterTable = new Hashtable<Character, Integer>();
        String[] letters = {"abcdefghijklmnopqrstuvwxyz"};
        for(int i = 0; i < letters[0].length(); i++){
            Character letter = letters[0].charAt(i);
            letterTable.put(letter, primes[i]);
        }
    }
    /**
     * Compute the hash code of the input string
     */
    private void addWord(String s){
        long hashS = myHashCode(s);
        // Retrieve the list of anagrams associated with the hash code, or create a new list if it doesn't exist
        ArrayList<String> anagrams = anagramTable.getOrDefault(hashS, new ArrayList<>());

        // Add the input string to the list of anagrams
        anagrams.add(s);

        // Store the list of anagrams in the hash table
        anagramTable.put(hashS, (ArrayList<String>) anagrams);
    }
    /**
     * Computes the hash code of a string using the prime number mapping
     * @param s
     * @return
     */
    private Long myHashCode(String s){
        long stringHash = 1;
        char[] charArr = s.toCharArray();
        for (int i = 0; i < charArr.length; i++) {
            Integer prime = letterTable.get(charArr[i]);
            if (prime == null) {
                throw new IllegalArgumentException("Invalid character in word: " + charArr[i]);
        }
            stringHash *= prime;
    }
        return stringHash;
    }
    /**
     * Reads a file containing words and adds them to the anagramTable
     * @param s
     * @throws IOException
     */
    private void processFile(String s) throws IOException{
        FileInputStream fstream = new FileInputStream(s);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        String strLine;
        while((strLine = br.readLine()) != null){
            this.addWord(strLine);
        }
        br.close();
    }
    /**
     * Gets the list of hash codes that correspond to the maximum number of anagrams
     * @return
     */
    private ArrayList<Map.Entry<Long,ArrayList<String>>> getMaxEntries(){
        int max = 0;
        ArrayList<Map.Entry<Long, ArrayList<String>>> maxList = new ArrayList<>();
        for (Map.Entry<Long, ArrayList<String>> entry : anagramTable.entrySet()) {
            int size = entry.getValue().size();
            if (size > max) {
                maxList.clear();
                maxList.add(entry);
                max = size;
            } else if (size == max) {
                maxList.add(entry);
            }
    }
    return maxList;
    }
    /**
     * @param args
     */
    public static void main(String[] args){
        Anagrams a = new Anagrams();
        final long startTime = System.nanoTime();
        try{
            a.processFile("words_alpha.txt");
        }  catch (IOException e1) {
            e1.printStackTrace();
        }
        ArrayList <Map.Entry <Long, ArrayList<String>>> maxEntries = a.getMaxEntries();
        final long estimatedTime = System.nanoTime() - startTime;
        final double seconds = ((double)estimatedTime/1000000000);
        long key = maxEntries.get(0).getKey();
        int length = maxEntries.get(0).getValue().size();
        
        System.out.println("Time: " + seconds);
        System.out.println("Key of max anagrams: " + key);
        System.out.println("List of max anagrams: " + maxEntries.get(0).getValue());
        System.out.println("Length of list of max anagrams: " + length);
    }
}
