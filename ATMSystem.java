package p2;
import java.util.Scanner;
public class ATMSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static String[] accountNumbers = {"123456", "789012", "345678"};
    private static String[] pins = {"1111", "2222", "3333"};
    private static double[] balances = {1000.0, 2000.0, 1500.0};

    public static void main(String[] args) {
        // Display welcome message
        System.out.println("Welcome to the ATM System!");

        // User authentication
        String accountNumber = authenticateUser();
        int userIndex = getUserIndex(accountNumber);

        // Display main menu
        boolean exit = false;
        while (!exit) {
            displayMainMenu();

            // Get user choice
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    checkBalance(balances[userIndex]);
                    break;
                case 2:
                    withdraw(balances, userIndex);
                    break;
                case 3:
                    deposit(balances, userIndex);
                    break;
                case 4:
                    transfer(balances, userIndex);
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        // Display exit message
        System.out.println("Thank you for using the ATM System!");
    }

    private static String authenticateUser() {
        System.out.println("Enter your account number: ");
        String accountNumber = scanner.next();

        System.out.println("Enter your PIN: ");
        String pin = scanner.next();

        for (int i = 0; i < accountNumbers.length; i++) {
            if (accountNumbers[i].equals(accountNumber) && pins[i].equals(pin)) {
                System.out.println("Authentication successful!");
                return accountNumber;
            }
        }

        System.out.println("Authentication failed. Invalid account number or PIN.");
        System.exit(0);
        return null;
    }

    private static int getUserIndex(String accountNumber) {
        for (int i = 0; i < accountNumbers.length; i++) {
            if (accountNumbers[i].equals(accountNumber)) {
                return i;
            }
        }
        return -1;
    }

    private static void displayMainMenu() {
        System.out.println("\nMain Menu:");
        System.out.println("1. Check Balance");
        System.out.println("2. Withdraw");
        System.out.println("3. Deposit");
        System.out.println("4. Transfer");
        System.out.println("5. Exit");
        System.out.print("Choose an option: ");
    }

    private static void checkBalance(double balance) {
        System.out.println("Your balance is: $" + balance);
    }

    private static void withdraw(double[] balances, int userIndex) {
        System.out.println("Enter the amount to withdraw: $");
        double amount = scanner.nextDouble();

        if (amount > 0 && amount <= balances[userIndex]) {
            balances[userIndex] -= amount;
            System.out.println("Withdrawal successful. Remaining balance: $" + balances[userIndex]);
        } else {
            System.out.println("Invalid amount or insufficient funds.");
        }
    }

    private static void deposit(double[] balances, int userIndex) {
        System.out.println("Enter the amount to deposit: $");
        double amount = scanner.nextDouble();

        if (amount > 0) {
            balances[userIndex] += amount;
            System.out.println("Deposit successful. New balance: $" + balances[userIndex]);
        } else {
            System.out.println("Invalid amount.");
        }
    }

    private static void transfer(double[] balances, int userIndex) {
        System.out.println("Enter the account number to transfer to: ");
        String recipientAccountNumber = scanner.next();

        int recipientIndex = getUserIndex(recipientAccountNumber);

        if (recipientIndex != -1) {
            System.out.println("Enter the amount to transfer: $");
            double amount = scanner.nextDouble();

            if (amount > 0 && amount <= balances[userIndex]) {
                balances[userIndex] -= amount;
                balances[recipientIndex] += amount;
                System.out.println("Transfer successful. Remaining balance: $" + balances[userIndex]);
            } else {
                System.out.println("Invalid amount or insufficient funds.");
            }
        } else {
            System.out.println("Recipient account not found.");
        }
    }
}
