package com.mutualfundcalculator.model;

public class MutualFund {
    private String ticker;
    private String name;
    private double beta;
    private double futureValue; // Add this field

    public MutualFund(String ticker, String name) {
        this.ticker = ticker;
        this.name = name;
    }

    // Getters and Setters
    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBeta() {
        return beta;
    }

    public void setBeta(double beta) {
        this.beta = beta;
    }

    public double getFutureValue() {
        return futureValue;
    }

    public void setFutureValue(double futureValue) {
        this.futureValue = futureValue;
    }
}