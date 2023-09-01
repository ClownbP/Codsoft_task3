import java.util.Scanner;

class BankAccount {
    private String accountNumber;
    private double balance;

    public BankAccount(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return true;
        }
        return false;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public double checkBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}

class ATM {
    private BankAccount bankAccount;
    private Scanner scanner;

    public ATM(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        System.out.println("Welcome to the ATM!");
        System.out.println("Account Number: " + bankAccount.getAccountNumber());
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Check Balance");
        System.out.println("4. Exit");
    }

    public void run() {
        while (true) {
            displayMenu();
            System.out.print("Enter your choice (1/2/3/4): ");
            String choice = scanner.next();
            switch (choice) {
                case "1":
                    System.out.print("Enter the amount to withdraw: ");
                    if (scanner.hasNextDouble()) {
                        double withdrawAmount = scanner.nextDouble();
                        if (bankAccount.withdraw(withdrawAmount)) {
                            System.out.println("Withdrew $" + withdrawAmount);
                        } else {
                            System.out.println("Insufficient balance or invalid input.");
                        }
                    } else {
                        System.out.println("Invalid input. Please enter a numeric amount.");
                        scanner.next(); // Clear invalid input
                    }
                    break;
                case "2":
                    System.out.print("Enter the amount to deposit: ");
                    if (scanner.hasNextDouble()) {
                        double depositAmount = scanner.nextDouble();
                        if (bankAccount.deposit(depositAmount)) {
                            System.out.println("Deposited $" + depositAmount);
                        } else {
                            System.out.println("Invalid input.");
                        }
                    } else {
                        System.out.println("Invalid input. Please enter a numeric amount.");
                        scanner.next(); // Clear invalid input
                    }
                    break;
                case "3":
                    double balance = bankAccount.checkBalance();
                    System.out.println("Your balance is $" + balance);
                    break;
                case "4":
                    System.out.println("Thank you for using the ATM!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please select 1, 2, 3, or 4.");
            }
        }
    }
}

public class Task {
    public static void main(String[] args) {
        BankAccount account = new BankAccount("123456789", 1000.0);
        ATM atm = new ATM(account);
        atm.run();
    }
}
