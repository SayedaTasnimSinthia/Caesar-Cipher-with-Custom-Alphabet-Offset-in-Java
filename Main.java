import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String encryptedResult = "";
        String decryptedResult = "";

        Scanner input = new Scanner(System.in);

       
        System.out.print("Enter value of A : ");
        String aInput = input.nextLine().trim();
        int value = 0;

        try {
            value = Integer.parseInt(aInput);

            if (value < 0) {
                System.out.println("Value of A cannot be less than 0");
                System.exit(0);
            }

        } 
        

        catch (NumberFormatException e) {
            System.out.println("Value of A must be a valid number");
            System.exit(0);
        }

     

        //================================================Custom Alphabet Mapping================================================


        int[] alphabetA = new int[26];
        for (int i = 0; i < 26; i++) {
            alphabetA[i] = value + i;
        }
        

      
        System.out.print("Enter the key value (number) : ");
        String keyInput = input.nextLine().trim();
        int keyValue = 0;


        try {
            keyValue = Integer.parseInt(keyInput);

            if (keyValue < 0) {
                System.out.println("Key value cannot be less than 0");
                System.exit(0);
            }
        } 
        
        
        catch (NumberFormatException e) {
            System.out.println("Key must be a valid number");
            System.exit(0);
        }

      
        
        System.out.print("Enter the word you want to encrypt : ");
        String rawInput = input.nextLine().toUpperCase();
        
      
        if (!rawInput.matches("[A-Z\\s]+")) {
            System.out.println("Invalid characters detected. Only letters & spaces are allowed.");
            System.exit(0); 
        }
        

        String word = rawInput.replaceAll("\\s", "");
        

        if (word.isEmpty()) {
            System.out.println("Error: You must enter at least one letter.");
            System.exit(0);
        }



        


        //================================================ENCRYPTION================================================
       
        for (int i = 0; i < word.length(); i++) {
            char currentLetter = word.charAt(i);
            int index = currentLetter - 'A';

   
         

            int c1 = alphabetA[index];
            c1 = c1 + keyValue;

            int shiftedIndex = (c1 - value) % 26;
            if (shiftedIndex < 0) {
                shiftedIndex += 26;
            }


            int targetCustomValue = value + shiftedIndex;

            for (int j = 0; j < 26; j++) {
                if (alphabetA[j] == targetCustomValue) {
                    char newLetter = (char) ('A' + j);
                    encryptedResult += newLetter;
                    break;
                }
            }
        }

        System.out.println("Encrypted Value : " + encryptedResult);



        // ================================================DECRYPTION================================================

        for (int i = 0; i < encryptedResult.length(); i++) {
            char currentLetter2 = encryptedResult.charAt(i);
            int index2 = currentLetter2 - 'A';


            if (index2 < 0 || index2 >= 26) {
                decryptedResult += currentLetter2;
                continue;
            }

            int c1 = alphabetA[index2];
            int originalV = c1 - keyValue;

            
            int originalIndex = (originalV - value) % 26;
            if (originalIndex < 0) {
                originalIndex += 26;
            }


            int targetOriginalValue = value + originalIndex;

            
            for (int j = 0; j < 26; j++) {
                if (alphabetA[j] == targetOriginalValue) {
                    char newLetter = (char) ('A' + j);
                    decryptedResult += newLetter;
                    break;
                }
            }
        }

        System.out.println("Decrypted Value : " + decryptedResult);

        input.close();
    }
}