package com.mutualfundcalculator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor // Generates constructor with all fields
@NoArgsConstructor  // Generates a no-argument constructor
public class MutualFund {
    private String ticker;
    private String name;
    private double beta;
}
