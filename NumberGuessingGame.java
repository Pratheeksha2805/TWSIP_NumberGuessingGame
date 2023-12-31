import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    public static void main(String[] args) {
        // Project Setup
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // Implement Random Number Generation
        int lowerBound = 1;
        int upperBound = 100;
        int randomNumber = generateRandomNumber(lowerBound, upperBound);

        // Create User Interface
        displayWelcomeMessage();
        displayInstructions(lowerBound, upperBound);

        // Game Loop
        int attempts = 0;
        boolean hasGuessedCorrectly = false;

        while (!hasGuessedCorrectly) {
            // Accept User Input
            int userGuess = getUserInput(scanner);

            // Compare Guess with the Random Number
            provideFeedback(userGuess, randomNumber);

            attempts++;

            // Track Attempts and Check Win/Lose Conditions
            if (attempts == 5 && !hasGuessedCorrectly) {
                displayLoseMessage(randomNumber);
                return;
            }

            if (userGuess == randomNumber) {
                hasGuessedCorrectly = true;
            }
        }

        // Display Win Message
        displayWinMessage(attempts);

        // Close Scanner
        scanner.close();
    }

    private static int generateRandomNumber(int lowerBound, int upperBound) {
        Random random = new Random();
        return random.nextInt(upperBound - lowerBound + 1) + lowerBound;
    }

    private static void displayWelcomeMessage() {
        System.out.println("Welcome to the Number Guessing Game!");
    }

    private static void displayInstructions(int lowerBound, int upperBound) {
        System.out.println("Guess the number between " + lowerBound + " and " + upperBound);
    }

    private static int getUserInput(Scanner scanner) {
        System.out.print("Enter your guess: ");
        return scanner.nextInt();
    }

    private static void provideFeedback(int userGuess, int randomNumber) {
        if (userGuess < randomNumber) {
            System.out.println("Your Guess Number is Smaller");
        } else if (userGuess > randomNumber) {
            System.out.println("Your Guess Number is Greater");
        } else {
            System.out.println("Congratulations! You've guessed the correct number!");
        }
    }

    private static void displayLoseMessage(int randomNumber) {
        System.out.println("Sorry, you've run out of attempts. The correct number was: " + randomNumber);
    }

    private static void displayWinMessage(int attempts) {
        System.out.println("Congratulations! You've guessed the correct number in " + attempts + " attempts.");
    }
}
