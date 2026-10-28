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
            System.out.println("6. Cancel Token");
            System.out.println("7. Service-wise Queue Summary");
            System.out.println("8. Exit");
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
                    System.out.print("Enter token number to cancel: ");
                    int cancelNumber = sc.nextInt();
                    sc.nextLine();
                    cancelToken(queue, cancelNumber);
                    break;

                case 7:
                    displayServiceSummary(queue);
                    break;

                case 8:
                    System.out.println("Exiting QueueWise. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        }while(choice!=8);
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



    public static void cancelToken(ArrayList<Token> queue, int tokenNumber) {
        for (Token token : queue) {
            if (token.getTokenNumber() == tokenNumber) {

                if (token.getStatus().equals("COMPLETED")) {
                    System.out.println("Cannot cancel a completed token.");
                    return;
                }

                if (token.getStatus().equals("CANCELLED")) {
                    System.out.println("This token is already cancelled.");
                    return;
                }

                token.setStatus("CANCELLED");
                System.out.println("Token " + tokenNumber + " cancelled successfully!");
                return;
            }
        }

        System.out.println("Token number not found.");
    }



    public static void displayQueue(ArrayList<Token> queue){
        for(Token token : queue){
            token.displayToken();
        }
    }



    public static void displayServiceSummary(ArrayList<Token> queue) {
        System.out.println("\n===== Service-wise Queue Summary =====");

        if (queue.isEmpty()) {
            System.out.println("No tokens available.");
            return;
        }

        ArrayList<String> services = new ArrayList<>();

        // Collect unique service names
        for (Token token : queue) {
            String service = token.getServiceName();

            boolean serviceExists = false;

            for (String existingService : services) {
                if (existingService.equalsIgnoreCase(service)) {
                    serviceExists = true;
                    break;
                }
            }

            if (!serviceExists) {
                services.add(service);
            }
        }

        // Count each status for every service
        for (String service : services) {
            int waiting = 0;
            int serving = 0;
            int completed = 0;
            int cancelled = 0;

            for (Token token : queue) {
                if (token.getServiceName().equalsIgnoreCase(service)) {
                    switch (token.getStatus()) {
                        case "WAITING":
                            waiting++;
                            break;
                        case "SERVING":
                            serving++;
                            break;
                        case "COMPLETED":
                            completed++;
                            break;
                        case "CANCELLED":
                            cancelled++;
                            break;
                    }
                }
            }

            System.out.println("\nService: " + service);
            System.out.println("Waiting   : " + waiting);
            System.out.println("Serving   : " + serving);
            System.out.println("Completed : " + completed);
            System.out.println("Cancelled : " + cancelled);
        }

        System.out.println("\n======================================");
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
        int cancelled = 0;


        for (Token token : queue) {
            if (token.getStatus().equals("WAITING")) {
                waiting++;
            } else if (token.getStatus().equals("SERVING")) {
                serving++;
            } else if (token.getStatus().equals("COMPLETED")) {
                completed++;
            } else if (token.getStatus().equals("CANCELLED")) {
                cancelled++;
            }
        }
        System.out.println("\n===== Queue Statistics =====");
        System.out.println("Total Tokens     : " + total);
        System.out.println("Waiting Tokens   : " + waiting);
        System.out.println("Serving Tokens   : " + serving);
        System.out.println("Completed Tokens : " + completed);
        System.out.println("Cancelled Tokens : " + cancelled);
        System.out.println("============================");
    }
}
