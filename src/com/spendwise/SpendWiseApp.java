package com.spendwise;

import java.util.ArrayList;
import java.util.Scanner;

public class SpendWiseApp {

    static ArrayList<Expense> expenseList = new ArrayList<>();
    static int expenseCounter = 1;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== SpendWise Menu =====");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Total Expenses");
            System.out.println("4. Delete Expense");
            System.out.println("5. Search by Category");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    addExpense(scanner);
                    break;

                case 2:
                    viewExpenses();
                    break;

                case 3:
                    calculateTotal();
                    break;

                case 4:
                    deleteExpense(scanner);
                    break;

                case 5:
                    searchByCategory(scanner);
                    break;

                case 6:
                    System.out.println("Exiting SpendWise...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 6);

        scanner.close();
    }

    // Add Expense
    public static void addExpense(Scanner scanner) {
        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();  // clear buffer

        System.out.print("Enter category: ");
        String category = scanner.nextLine();

        System.out.print("Enter date (dd-mm-yyyy): ");
        String date = scanner.nextLine();

        Expense expense = new Expense(expenseCounter++, amount, category, date);
        expenseList.add(expense);

        System.out.println("Expense added successfully!");
    }

    // View Expenses
    public static void viewExpenses() {
        if (expenseList.isEmpty()) {
            System.out.println("No expenses recorded.");
            return;
        }

        for (Expense e : expenseList) {
            System.out.println(e);
        }
    }

    // Calculate Total
    public static void calculateTotal() {
        double total = 0;

        for (Expense e : expenseList) {
            total += e.getAmount();
        }

        System.out.println("Total Expenses: " + total);
    }

    // Delete Expense
    public static void deleteExpense(Scanner scanner) {

        if (expenseList.isEmpty()) {
            System.out.println("No expenses to delete.");
            return;
        }

        System.out.print("Enter Expense ID to delete: ");
        int id = scanner.nextInt();

        boolean found = false;

        for (int i = 0; i < expenseList.size(); i++) {
            if (expenseList.get(i).getId() == id) {
                expenseList.remove(i);
                found = true;
                System.out.println("Expense deleted successfully!");
                break;
            }
        }

        if (!found) {
            System.out.println("Expense ID not found.");
        }
    }

    // Search by Category
    public static void searchByCategory(Scanner scanner) {

        if (expenseList.isEmpty()) {
            System.out.println("No expenses recorded.");
            return;
        }

        scanner.nextLine();  // clear buffer
        System.out.print("Enter category to search: ");
        String searchCategory = scanner.nextLine();

        boolean found = false;

        for (Expense e : expenseList) {
            if (e.getCategory().equalsIgnoreCase(searchCategory)) {
                System.out.println(e);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No expenses found in this category.");
        }
    }
}