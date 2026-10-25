package com.queuewise;

import com.queuewise.model.Token;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<Token> queue = new ArrayList<>();
        queue.add(new Token(101, "Tanishka", "Bonafide Certificate"));
        queue.add(new Token(102, "Ananya", "ID Card"));
        queue.add(new Token(103, "Rahul", "Fee Payment"));

        int choice;
        do {
            System.out.println("1. Call Next Token");
            System.out.println("2. View All Tokens");
            System.out.println("3. Add New Token");
            System.out.println("4. Complete Current Token");
            System.out.println("5. View Queue Statistics");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    callNextToken(queue);
                    break;

                case 2:
                    displayQueue(queue);
                    break;

                case 3:

                    sc.nextLine();

                    System.out.print("Enter customer name: ");
                    String customerName = sc.nextLine().trim();

                    if (customerName.isEmpty()) {
                        System.out.println("Customer name cannot be empty.");
                        break;
                    }

                    System.out.print("Enter service name: ");
                    String serviceName = sc.nextLine().trim();

                    if (serviceName.isEmpty()) {
                        System.out.println("Service name cannot be empty.");
                        break;
                    }


                    int tokenNumber = generateTokenNumber(queue);

                    Token newToken = new Token(
                            tokenNumber, customerName, serviceName
                    );

                    queue.add(newToken);

                    System.out.println("Token created successfully!");
                    newToken.displayToken();
                    break;

                case 4:
                    System.out.print("Enter token number to complete: ");
                    int number = sc.nextInt();
                    sc.nextLine();
                    completeToken(queue, number, sc);
                    break;

                case 5:
                    displayStatistics(queue);
                    break;

                case 6:
                    System.out.println("Exiting QueueWise. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        }while(choice!=6);
        sc.close();

    }

    public static void callNextToken(ArrayList<Token> queue) {

        // Check whether a token is already being served
        for (Token token : queue) {
            if (token.getStatus().equals("SERVING")) {
                System.out.println("A token is already being served.");
                return;
            }
        }

        // Call the first waiting token
        for (Token token : queue) {
            if (token.getStatus().equals("WAITING")) {
                token.setStatus("SERVING");

                System.out.println("\nCalling Token Number: "
                        + token.getTokenNumber());
                return;
            }
        }

        System.out.println("No waiting tokens available.");
    }


    public static void completeToken(ArrayList<Token> queue, int tokenNumber, Scanner sc) {

    for (Token token : queue) {

        if (token.getTokenNumber() == tokenNumber
                && token.getStatus().equals("SERVING")) {
            System.out.print("Confirm completion of Token "
                    + tokenNumber + "? (yes/no): ");

            String confirmation = sc.nextLine().trim();
            if (confirmation.equalsIgnoreCase("yes")) {
                token.setStatus("COMPLETED");
                System.out.println("Token " + tokenNumber + " completed!");
            } else {
                System.out.println("Token completion cancelled.");
            }
            return;
        }
    }

        System.out.println("Token cannot be completed. It may not be serving.");
    }


    public static void displayQueue(ArrayList<Token> queue){
        for(Token token : queue){
            token.displayToken();
        }
    }


    public static int generateTokenNumber(ArrayList<Token> queue) {
        int highestTokenNumber = 100;

        for (Token token : queue) {
            if (token.getTokenNumber() > highestTokenNumber) {
                highestTokenNumber = token.getTokenNumber();
            }
        }

        return highestTokenNumber + 1;
    }


    public static void displayStatistics(ArrayList<Token> queue) {
        int total = queue.size();
        int waiting = 0;
        int serving = 0;
        int completed = 0;

        for (Token token : queue) {
            if (token.getStatus().equals("WAITING")) {
                waiting++;
            } else if (token.getStatus().equals("SERVING")) {
                serving++;
            } else if (token.getStatus().equals("COMPLETED")) {
                completed++;
            }
        }

        System.out.println("\n===== Queue Statistics =====");
        System.out.println("Total Tokens     : " + total);
        System.out.println("Waiting Tokens   : " + waiting);
        System.out.println("Serving Tokens   : " + serving);
        System.out.println("Completed Tokens : " + completed);
        System.out.println("============================");
    }
}
