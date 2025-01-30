package com.mutualfundcalculator.controller;

import com.mutualfundcalculator.model.MutualFund;
import com.mutualfundcalculator.service.MutualFundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")  // Allow Angular frontend

@RestController
@RequestMapping("/api/mutualfunds")
public class MutualFundController {

    @Autowired
    private MutualFundService mutualFundService;

    @GetMapping
    public List<MutualFund> getAllMutualFunds() {
        return mutualFundService.getAllMutualFunds();
    }

    // ✅ Fix: Only pass `ticker`, not `principal` and `time`
    @GetMapping("/ticker/{ticker}")
    public MutualFund getMutualFundByTicker(@PathVariable String ticker) {
        return mutualFundService.getMutualFundByTicker(ticker);
    }
}
