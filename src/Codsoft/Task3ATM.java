package Codsoft;

import java.util.Scanner; 

public class Task3ATM { 
    private Task3BankAccount account;
    private Scanner scanner;

    public Task3ATM(Task3BankAccount account) {  
        this.account = account;
        this.scanner = new Scanner(System.in);
    }

     
    public void run() {
        while (true) {
        	System.out.println("\n<<< ATM Operation >>>");
            System.out.println("\nService Selection");
            System.out.println("  1. Withdraw");
            System.out.println("  2. Deposit");
            System.out.println("  3. Check Balance");
            System.out.println("  4. Exit");

            System.out.print("\nChoose an option: ");
            int option = getValidInt();

            switch (option) {
                case 1:
                    withdraw();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    checkBalance();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();  
                    return;
                default:
                    System.out.println("\nInvalid option. Please try again.");
            }
        }
    }

    private void withdraw() {
        System.out.print("\nEnter amount to withdraw: ");
        double amount = getValidDouble();

        if (account.withdraw(amount)) {
            System.out.println("Withdrawal successful.\nNew balance: " + account.getBalance());
        } else {
            System.out.println("Insufficient balance or invalid withdrawal amount.");
        }
    }

    private void deposit() {
        System.out.print("\nEnter amount to deposit: ");
        double amount = getValidDouble();

        if (account.deposit(amount)) {
            System.out.println("Deposit successful. New balance: " + account.getBalance());
        } else {
            System.out.println("Deposit failed. Please enter a valid amount.");
        }
    }

    private void checkBalance() {
        System.out.println("\nCurrent balance: " + account.getBalance());
    }

  
    private int getValidInt() {
        while (!scanner.hasNextInt()) {
            System.out.print("\nInvalid input. Please enter a number: ");
            scanner.next(); 
        }
        return scanner.nextInt();
    }

   
    private double getValidDouble() {
        while (!scanner.hasNextDouble()) {
            System.out.print("\nInvalid amount. Please enter a valid number: ");
            scanner.next();
        }
        return scanner.nextDouble();
    }

    public static void main(String[] args) {
        Task3BankAccount account = new Task3BankAccount(1000.0);  
        Task3ATM atm = new Task3ATM(account);  
        atm.run();  
    }
}
