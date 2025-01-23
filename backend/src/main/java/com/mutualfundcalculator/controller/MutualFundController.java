package com.mutualfundcalculator.controller;

import com.mutualfundcalculator.model.MutualFund;
import com.mutualfundcalculator.service.MutualFundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mutualfunds")
public class MutualFundController {

    @Autowired
    private MutualFundService mutualFundService;

    @GetMapping
    public List<MutualFund> getAllMutualFunds() {
        return mutualFundService.getAllMutualFunds();
    }

    @GetMapping("/{ticker}")
public MutualFund getMutualFundByTicker(
        @PathVariable String ticker,
        @RequestParam double principal,
        @RequestParam int time) {
    return mutualFundService.getMutualFundByTicker(ticker, principal, time);
}
}