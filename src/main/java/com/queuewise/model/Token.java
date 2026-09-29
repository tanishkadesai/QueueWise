package com.queuewise.model;

public class Token {
    private int tokenNumber;
    private String customerName;
    private String serviceName;
    private String status;

    public Token(int tokenNumber, String customerName, String serviceName){
        this.tokenNumber = tokenNumber;
        this.customerName = customerName;
        this.serviceName = serviceName;
        this.status = "WAITING";
    }
    public int getTokenNumber(){
        return tokenNumber;
    }

    public String getCustomerName(){
        return customerName;
    }

    public String getServiceName(){
        return serviceName;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(){
        this.status = status;
    }

    public void displayToken(){
        System.out.println("---- Queuewise Token ----");
        System.out.println("Token number : " + tokenNumber);
        System.out.println("Customer name : " + customerName);
        System.out.println("Service number : " + serviceName);
        System.out.println("Status : " + status);
    }
}
