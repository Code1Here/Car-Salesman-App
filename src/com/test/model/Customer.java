package com.test.model;

import java.text.DecimalFormat;

public class Customer {

    private String name;//name of customer
    private float grossPay;//yearly pay before taxes to calculate interest and required down payment
    private String number;//number for follow up calls
    private String carModel;//Name of tesla pick
    private float carPrice;//price of tesla they had chosen
    private final String ssn; // DB primary key


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

    public float getGrossPay() {
        return grossPay;
    }

    public String getNumber() {
        return number;
    }

    public float getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(int carPrice) {
        this.carPrice = carPrice;
    }

    public String getCarType() {
        return carModel;
    }

    public void setCarType(String carType) {
        this.carModel = carType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGrossPay(int grossPay) {
        this.grossPay = grossPay;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void format() {
        StringBuilder[] array = {new StringBuilder(name), new StringBuilder(number)};
        for (int i = 0; i < array.length; i++) {
            int num = 40 - array[i].length();
            for (int j = array[i].length(); j < num; j++) {
                array[i].append(" ");
            }
            if (i == 0)
                setName(array[i].toString());
            if (i == 1)
                setNumber(array[i].toString());
        }
    }

    public String formatGross() {
        String newString = String.format("%.2f", grossPay);
        double amount = Double.parseDouble(newString);
        DecimalFormat formatter = new DecimalFormat("#,###.00");
        return formatter.format(amount);
    }

    public String formatCarPrice() {
        String newString = String.format("%.2f", carPrice);
        double amount = Double.parseDouble(newString);
        DecimalFormat formatter = new DecimalFormat("#,###.00");
        return formatter.format(amount);
    }
}