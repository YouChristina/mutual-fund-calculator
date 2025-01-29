package com.mutualfundcalculator.model;

public class MutualFund {
    private String ticker;
    private String name;
    private double beta;
    private double futureValue;

    public MutualFund(String ticker, String name) {
        this.ticker = ticker;
        this.name = name;
        this.beta = 1.0; // Default in case API fails
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

    public void updateBetaFromApiResponse(double apiBeta) {
        this.beta = apiBeta;
    }

    @Override
    public String toString() {
        return "MutualFund{" +
                "ticker='" + ticker + '\'' +
                ", name='" + name + '\'' +
                ", beta=" + beta +
                ", futureValue=" + futureValue +
                '}';
    }
}
