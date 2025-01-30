package com.mutualfundcalculator.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BetaResponse {

    @JsonProperty("status")
    private String status; 

    @JsonProperty("statusMessage")
    private String statusMessage;

    @JsonProperty("data")
    private double beta; 

    public double getBeta() {
        return beta;
    }

    public void setBeta(double beta) {
        this.beta = beta;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    @Override
    public String toString() {
        return "BetaResponse{status='" + status + "', statusMessage='" + statusMessage + "', beta=" + beta + "}";
    }
}