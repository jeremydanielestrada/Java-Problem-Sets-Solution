import java.io.*;
import java.util.*;

public class WordCounter {

    public static void main(String[] args) {
        String inputFile = "input.txt";
        String stopwordsFile = "stopwords.txt";
        String outputFile = "output.txt";
        try{
           Set<String>stopWords = loadStopWords(stopwordsFile); 

           Map<String, Integer>wordCount = countWords(inputFile, stopWords);


           displaySortedWords(wordCount, outputFile);

        }catch(IOException e){
            System.out.println(e.getMessage());
        }


    }

    //Load Stop Words;    
    static Set<String> loadStopWords(String stopwordsFile)throws IOException {
        Set<String>stopwords = new HashSet<>();
        BufferedReader br = new BufferedReader(new FileReader(stopwordsFile));
        String line;

        while((line = br.readLine())!= null){
            stopwords.add(line.toLowerCase().trim());
        }

        br.close();
        return stopwords;

    }



    //Count words
    static Map<String, Integer> countWords(String inputFile, Set<String> stopwords) throws IOException {
        Map<String, Integer> wordCount =  new HashMap<>();
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        String line;

        while((line = reader.readLine()) != null){
            String[] words = line.toLowerCase()
                            .replaceAll("[^a-z ]", "")//remove punctuation
                            .trim()
                            .split("\\s+");

            for(String word: words){
                if(!word.isEmpty() && !stopwords.contains(word)){
                    wordCount.put(word, wordCount.getOrDefault(word, 0)+1);
                }

            }
        }

        reader.close();
        return wordCount;
    }


    static void displaySortedWords(Map<String, Integer> wordCount, String outputFile) throws IOException{
        List<Map.Entry<String, Integer>> sortedWords =  new ArrayList<>(wordCount.entrySet());
        BufferedWriter writer  = new BufferedWriter(new FileWriter(outputFile));
    
        sortedWords.sort((a, b) -> b.getValue().compareTo(a.getValue()));
        
        for(Map.Entry<String, Integer> entry: sortedWords){
            System.out.println(entry.getKey() + " -> " + entry.getValue());
            writer.write(entry.getKey() + " : " + entry.getValue() + "\n");
        }

        writer.close();

    }
}
