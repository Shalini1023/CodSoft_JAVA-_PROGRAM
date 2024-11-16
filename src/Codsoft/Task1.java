package Codsoft;
import java.util.Random;
import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        Scanner ng = new Scanner(System.in);
        Random random = new Random();
        
        int numberOfRounds = 0;
        int totalAttempts = 0;
        int wins = 0;

        while (true) {
            numberOfRounds++;
  // Rule 1: Generate a random number within a specified range (1-100)
            int numberToGuess = random.nextInt(100) + 1;
            int attempts = 0;
            boolean guessed = false;
            System.out.println(">>>>>> WELCOME TO THE NUMBER GAME! LETS PLAY! <<<<<<");
            System.out.println("\nRound " + numberOfRounds);
  // Rule 2: Prompt the user to enter their guess for the generated number
            System.out.println("Guess a number between 1 and 100, and you'll have 1 to 6 attempts to Guess it!");

            while (!guessed && attempts < 6) {
 // Rule 3: Compare the user's guess with the generated number
            	System.out.print("\nEnter your Guess: ");
                int userGuess = ng.nextInt();
                attempts++;

                if (userGuess < numberToGuess) {
                    System.out.println("Its too low!Try again.");
                } else if (userGuess > numberToGuess) {
                    System.out.println("Its too high!Try again.");
                } else {
                    System.out.println("\n***CONGRATULATIONS:)*** \n You guessed the number in "  + attempts + " attempts.");
                    guessed = true;
                    wins++;
                }
            }

 // Rule 4: Repeat steps 2-3 until the user guesses the correct number or runs out of attempts
            if (!guessed) {
                System.out.println("\nSorry, you didn't guess the number. The number was " + numberToGuess);
            }

            totalAttempts += attempts;

 // Rule 5: Limit the number of attempts the user has to guess the number
            System.out.println("Play again? (yes/no)");
            String input = ng.next();

 // Rule 6: Add the option for multiple rounds, allowing the user to play again
            if (!input.equalsIgnoreCase("yes")) {
                break;
            }
        }

 // Rule 7: Display the user's score, which can be based on the number of attempts taken or rounds won
        System.out.println("**GAME OVER** Thank you for Playing!");
        System.out.println("Number of Roundsplayed: " + numberOfRounds);
        System.out.println("Total Number of attempts: " + totalAttempts);
        System.out.println("Wins: " + wins);
        ng .close();
    }
}

