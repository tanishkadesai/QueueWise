package com.queuewise;

import com.queuewise.model.Token;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<Token> queue = new ArrayList<>();
        queue.add(new Token(101, "Tanishka", "Bonafide Certificate"));
        queue.add(new Token(102, "Ananya", "ID Card"));
        queue.add(new Token(103, "Rahul", "Fee Payment"));

        System.out.println("Before calling next token:");
        displayQueue(queue);

        callNextToken(queue);
        callNextToken(queue);

        completeToken(queue, 101);


        System.out.println("\nAfter calling next token:");
        displayQueue(queue);
    }
    public static void callNextToken(ArrayList<Token> queue){

        for(Token token : queue){
            if(token.getStatus().equals("WAITING")){
                token.setStatus("SERVING");

                System.out.println("\nCalling Token Number: " + token.getTokenNumber());
                break;
            }
        }
    }

public static void completeToken(ArrayList<Token> queue, int tokenNumber) {

    for (Token token : queue) {

        if (token.getTokenNumber() == tokenNumber
                && token.getStatus().equals("SERVING")) {

            token.setStatus("COMPLETED");

            System.out.println("Token " + tokenNumber + " completed!");
            return;
        }
    }
    System.out.println("Token cannot be completed.");
}


    public static void displayQueue(ArrayList<Token> queue){
        for(Token token : queue){
            token.displayToken();
        }
    }
}
