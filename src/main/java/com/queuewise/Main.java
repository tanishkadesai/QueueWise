package com.queuewise;

import com.queuewise.model.Token;

public class Main {

    public static void main(String[] args) {

        Token token1 = new Token(
                101,
                "Tanishka",
                "Bonafide Certificate"
        );

        Token token2 = new Token(
                102,
                "Rahul",
                "Fee Payment"
        );

        Token token3 = new Token(
                103,
                "Ananya",
                "ID card"
        );

        token1.displayToken();
        token2.displayToken();
        token3.displayToken();
    }
}
