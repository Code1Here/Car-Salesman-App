package com.test.model;

public class Customer {

    private String name;//name of customer
    private int grossPay;//yearly pay before taxes
    private String number;//number for follow up calls
    private String carModel;//Name of tesla pick
    private int carPrice;//price of tesla
    private String ssn;


    public Customer(String name, int grossPay, String number, String ssn) {
        this.name = name;
        this.grossPay = grossPay;
        this.number = number;
        this.ssn = ssn;
    }

    public String getSsn() {
        return ssn;
    }

    public String getName() {
        return name;
    }

    public int getGrossPay() {
        return grossPay;
    }

    public String getNumber() {
        return number;
    }

    public int getCarPrice() { return carPrice; }

    public void setCarPrice(int carPrice) { this.carPrice = carPrice; }

    public String getCarType() {
        return carModel;
    }

    public void setCarType(String carType) {
        this.carModel = carType;
    }
}