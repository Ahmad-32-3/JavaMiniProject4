/*
Filename: W12U3A3_MuhammadAhmad_HockeyCardSearch
Author:  Muhammad Ahmad
Date: Thursday, May 16, 2024
Purpose: To create a program that uses linear and binary search algorithms to find if a hockey player is in the card pile (in a seperate text file)
*/
import java.util.*;
import java.io.*;

public class Main {

    // Linear search algorithm
    public static String linearSearch(String[] A, int B) {
        for (int k = 0; k < A.length; k+=2) {
            if (Integer.parseInt(A[k]) == B) {
                return A[k + 1];
            }
        }
        return "L";
    }

    // Binary search algorithm
    public static String binarySearch(String[] A, int left, int right, int V) {
        int middle;

        while (left <= right) {
            middle = (int) (Math.round(left + right) / 2) * 2;
            if (V == Integer.parseInt(A[middle])) {
                return A[middle + 1];
            } else {
                if (V < Integer.parseInt(A[middle])) {
                    right = middle - 2;
                } else {
                    left = middle + 2;
                }
            }
        }
        return "L";
    }

    public static void main(String[] args) {
        // Set scanner to keyedInput
        Scanner keyedInput = new Scanner(System.in);

        // Initialize variables
        int userInput;
        String cardFound;
        String binaryCardFound;
        int optionChoice;

        // Read text file
        ArrayList<String> hockeyList = new ArrayList<String>();
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader("cardlist.txt"));
            String word;
            while ((word = br.readLine()) != null) {
                hockeyList.add(word);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        String[] cardList = new String[hockeyList.size()];
        hockeyList.toArray(cardList);

        while (true) { 
            // Give user menu of options
            System.out.print(
              "\nInput the corrosponding number for the following executables (1, 2)\n" +
                  "1. Search for hockey member\n" +
                  "2. Exit program\n\n" +
                  "Enter the number: ");

            // Take user input
            optionChoice = keyedInput.nextInt();

            // Use a switch statement to enact on chosen option
            switch (optionChoice) { 
                case 1:
                    // Get input from user
                    System.out.println("Please input the number of the hockeycard you want to check is in your hockey card pile");
                    userInput = keyedInput.nextInt();
            
                    // Use linear search algorithm
                    cardFound = linearSearch(cardList, userInput);

                    // Check if card is found by seeing if it returned "L" or not
                    if (cardFound != "L") {
                        System.out.println("Linear search: " + cardFound);
                    } else {
                        System.out.println("Linear search: No card was found");
                    }
            
                    // Use binary search algorithm
                    binaryCardFound = binarySearch(cardList, 0, cardList.length - 1, userInput);

                    // Check if card is found by seeing if it returned "L" or not
                    if (binaryCardFound != "L") {
                        System.out.println("Binary search: " + binaryCardFound);
                    } else {
                        System.out.println("Binary search: No card was found");
                    }
                    break;
                case 2:
                      System.out.println("\nThe program has ended, have a nice day.");
                      // Exit Code
                      System.exit(0);
                    
                // Default case if a number between 1 and 5 is not input
                default:
                      System.out.println("Invalid input. Enter either 1 or 2 to execute the corrosponding action.");
                      break;
            }
        }
    }
}