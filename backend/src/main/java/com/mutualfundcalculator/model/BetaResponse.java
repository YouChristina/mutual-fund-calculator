package com.mutualfundcalculator.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BetaResponse {

    private int status;

    @JsonProperty("statusMessage")
    private String statusMessage;

    @JsonProperty("data") 
    private Double beta; // Directly map "data" to beta

    public Double getBeta() {
        return beta != null ? beta : 1.0; // Default to 1.0 if null
    }

    public void setBeta(Double beta) {
        this.beta = beta;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
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
        return "BetaResponse{status=" + status + ", statusMessage='" + statusMessage + "', beta=" + beta + "}";
    }
}
