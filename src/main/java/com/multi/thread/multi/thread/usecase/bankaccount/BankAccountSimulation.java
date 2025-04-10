package com.multi.thread.multi.thread.usecase.bankaccount;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    // Synchronized method to deposit money
    public synchronized void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println(Thread.currentThread().getName() + " deposited " + amount + ", New balance: " + balance);
        }
    }

    // Synchronized method to withdraw money
    public synchronized void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println(Thread.currentThread().getName() + " withdrew " + amount + ", New balance:" + balance);
        } else {
            System.out.println(Thread.currentThread().getName() + " tried to withdraw " + amount
                    + ", but insufficient balance.");
        }
    }

    // Get current balance
    public double getBalance() {
        return balance;
    }
}

public class BankAccountSimulation {
    public static void main(String[] args) {
        // Create a bank account with an initial balance of 1000
        BankAccount account = new BankAccount(1000);
        // Create threads representing customers
        Thread customer1 = new Thread(() -> {
            account.deposit(500);
            account.withdraw(300);
        }, "Customer-1");
        Thread customer2 = new Thread(() -> {
            account.deposit(200);
            account.withdraw(100);
        }, "Customer-2");
        Thread customer3 = new Thread(() -> {
            account.withdraw(700);

            account.deposit(400);
        }, "Customer-3");
        // Start all customer threads
        customer1.start();
        customer2.start();
        customer3.start();
        // Wait for all threads to finish
        try {
            customer1.join();
            customer2.join();
            customer3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Final account balance: " + account.getBalance());
    }
}
