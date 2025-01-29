package com.mutualfundcalculator.service;

import com.mutualfundcalculator.model.MutualFund;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class MutualFundService {

    private List<MutualFund> mutualFunds;

    public MutualFundService() {
        mutualFunds = new ArrayList<>();
        // Add hardcoded mutual funds (without beta values)
        mutualFunds.add(new MutualFund("VSMPX", "Vanguard Total Stock Market Index Fund;Institutional Plus"));
        mutualFunds.add(new MutualFund("FXAIX", "Fidelity 500 Index Fund"));
        mutualFunds.add(new MutualFund("VFIAX", "Vanguard 500 Index Fund;Admiral"));
        mutualFunds.add(new MutualFund("VTSAX", "Vanguard Total Stock Market Index Fund;Admiral"));
        mutualFunds.add(new MutualFund("SPAXX", "Fidelity Government Money Market Fund"));
        mutualFunds.add(new MutualFund("VMFXX", "Vanguard Federal Money Market Fund;Investor"));
        mutualFunds.add(new MutualFund("FDRXX", "Fidelity Government Cash Reserves"));
        mutualFunds.add(new MutualFund("FGTXX", "Goldman Sachs FS Government Fund;Institutional"));
        mutualFunds.add(new MutualFund("SWVXX", "Schwab Value Advantage Money Fund;Investor"));
        mutualFunds.add(new MutualFund("VGTSX", "Vanguard Total International Stock Index Fund;Investor"));
        mutualFunds.add(new MutualFund("VFFSX", "Vanguard 500 Index Fund;Institutional Select"));
        mutualFunds.add(new MutualFund("VIIIX", "Vanguard Institutional Index Fund;Inst Plus"));
        mutualFunds.add(new MutualFund("OGVXX", "JPMorgan US Government Money Market Fund;Capital"));
        mutualFunds.add(new MutualFund("MVRXX", "Morgan Stanley Inst Liq Government Port;Institutional"));
        mutualFunds.add(new MutualFund("VTBNX", "Vanguard Total Bond Market II Index Fund;Institutional"));
        mutualFunds.add(new MutualFund("TFDXX", "BlackRock Liquidity FedFund;Institutional"));
        mutualFunds.add(new MutualFund("FRGXX", "Fidelity Instl Government Portfolio;Institutional"));
        mutualFunds.add(new MutualFund("TTTXX", "BlackRock Liquidity Treasury Trust Fund;Institutional"));
        mutualFunds.add(new MutualFund("AGTHX", "American Funds Growth Fund of America;A"));
        mutualFunds.add(new MutualFund("VTBIX", "Vanguard Total Bond Market II Index Fund;Investor"));
        mutualFunds.add(new MutualFund("GVMXX", "State Street US Government Money Market Fund;Prem"));
        mutualFunds.add(new MutualFund("FCTDX", "Fidelity Strategic Advisers Fidelity US Total Stk"));
        mutualFunds.add(new MutualFund("FCNTX", "Fidelity Contrafund"));
        mutualFunds.add(new MutualFund("VINIX", "Vanguard Institutional Index Fund;Institutional"));
        mutualFunds.add(new MutualFund("VMRXX", "Vanguard Cash Reserves Federal Money Market Fd;Adm"));
    }

    public List<MutualFund> getAllMutualFunds() {
        for (MutualFund fund : mutualFunds) {
            double beta = getBetaFromNewtonApi(fund.getTicker());
            fund.setBeta(beta); // Update the fund's beta
        }
        return mutualFunds;
    }
    
    
    public MutualFund getMutualFundByTicker(String ticker, double principal, int time) {
        for (MutualFund fund : mutualFunds) {
            if (fund.getTicker().equals(ticker)) {
                double beta = getBetaFromNewtonApi(ticker);
                fund.setBeta(beta);

                // Calculate future value
                double rate = 0.02 + beta * (0.08 - 0.02); // Risk-free rate = 2%, Market return = 8%
                double futureValue = calculateFutureValue(principal, rate, time);
                fund.setFutureValue(futureValue);

                return fund;
            }
        }
        return null;
    }

    private double getBetaFromNewtonApi(String ticker) {
        try {
            String url = "https://api.newtonanalytics.com/stock-beta/?ticker=" + ticker + "&index=^GSPC&interval=1mo&observations=12";
            System.out.println("Fetching beta from: " + url);
    
            RestTemplate restTemplate = new RestTemplate();
            BetaResponse response = restTemplate.getForObject(url, BetaResponse.class);
    
            if (response != null) {
                System.out.println("API Response: " + response); // Debugging
                return response.getBeta();
            } else {
                System.err.println("Beta API returned null for " + ticker);
            }
        } catch (Exception e) {
            System.err.println("Error fetching beta for " + ticker + ": " + e.getMessage());
        }
    
        return 1.0; // Default if API fails
    }
    
    
    private double calculateFutureValue(double principal, double rate, int time) {
        return principal * Math.exp(rate * time);
    }

    private static class BetaResponse {
        private double beta;

        public double getBeta() {
            return beta;
        }

        public void setBeta(double beta) {
            this.beta = beta;
        }
    }
}