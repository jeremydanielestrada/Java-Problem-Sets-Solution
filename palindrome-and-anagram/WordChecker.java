import java.util.Scanner;
import java.util.Arrays; 

public class WordChecker{
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){
        
        while(true){
            System.out.println("===== Palindrome & Anagram Tool =====");
            System.out.println("1. Palindrome Checker");
            System.out.println("2. Anagram Checker");
            System.out.println("3. Exit Program");
            String input = scanner.nextLine();


            switch (input) {
                case "1":
                    PalindromeChecker();
                    break;
                case "2":
                    AnagramChecker();
                    break;

                case "3":
                    System.out.println("3. Exiting Program...");
                    scanner.close();
                    return;
                default:
                    break;
            }



        }



    }


    static String IgnoreSpaceAndPunctuations(String word){
      return word.matches("[a-zA-Z ]+") 
       ?   word.replaceAll("[^a-zA-Z0-9]|\\s", "").toLowerCase() 
       : "Invalid Word"; 
    }


    static void PalindromeChecker(){
        System.out.println("Enter a single word or phrase");
        String word = scanner.nextLine();


      
     String  s = IgnoreSpaceAndPunctuations(word);

     System.out.println(s);

     String  reversedStr  = "";

    for(int i = 0; i < s.length(); i++){
       reversedStr = s.charAt(i) + reversedStr; //Reversed string iteration
    }

        String palindrome = reversedStr;
    
        if(s.equals(palindrome)){
            System.out.println("The word/phrase is a palindrome");
        } else {
            System.out.println("The word/phrase is not a palindrome");
        }
       

    }


    static void AnagramChecker(){
        System.out.println("Enter First Word");
        String firstWord = scanner.nextLine();

        IgnoreSpaceAndPunctuations(firstWord);

        System.out.println("Enter First Word");
        String secondWord = scanner.nextLine();

        IgnoreSpaceAndPunctuations(secondWord);

        char[] firstArray  = firstWord.toCharArray(); //Convert String into new character array using toCharrArayy
        char[] secondArray  = secondWord.toCharArray();

        Arrays.sort(firstArray);  //Array sort
        Arrays.sort(secondArray);


        if(Arrays.equals(firstArray, secondArray)){
            System.out.println("The words are anagrams");
        } else {
            System.out.println("The words are not anagrams");
        }

    }

    
}