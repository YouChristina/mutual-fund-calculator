package com.mutualfundcalculator.service;

import com.mutualfundcalculator.model.MutualFund;
import com.mutualfundcalculator.model.BetaResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class MutualFundService {

    private static final List<MutualFund> mutualFunds = List.of(
        new MutualFund("VSMPX", "Vanguard Total Stock Market Index Fund;Institutional Plus", 1.0),
        new MutualFund("FXAIX", "Fidelity 500 Index Fund", 1.0),
        new MutualFund("VFIAX", "Vanguard 500 Index Fund;Admiral", 1.0),
        new MutualFund("VTSAX", "Vanguard Total Stock Market Index Fund;Admiral", 1.0),
        new MutualFund("VMFXX", "Vanguard Federal Money Market Fund;Investor", 1.0),
        new MutualFund("FGTXX", "Goldman Sachs FS Government Fund;Institutional", 1.0),
        new MutualFund("SWVXX", "Schwab Value Advantage Money Fund;Investor", 1.0),
        new MutualFund("VGTSX", "Vanguard Total International Stock Index Fund;Investor", 1.0),
        new MutualFund("VFFSX", "Vanguard 500 Index Fund;Institutional Select", 1.0),
        new MutualFund("VIIIX", "Vanguard Institutional Index Fund;Inst Plus", 1.0),
        new MutualFund("MVRXX", "Morgan Stanley Inst Liq Government Port;Institutional", 1.0),
        new MutualFund("VTBNX", "Vanguard Total Bond Market II Index Fund;Institutional", 1.0),
        new MutualFund("AGTHX", "American Funds Growth Fund of America;A", 1.0),
        new MutualFund("VTBIX", "Vanguard Total Bond Market II Index Fund;Investor", 1.0),
        new MutualFund("GVMXX", "State Street US Government Money Market Fund;Prem", 1.0),
        new MutualFund("FCTDX", "Fidelity Strategic Advisers Fidelity US Total Stk", 1.0),
        new MutualFund("FCNTX", "Fidelity Contrafund", 1.0),
        new MutualFund("VINIX", "Vanguard Institutional Index Fund;Institutional", 1.0),
        new MutualFund("VMRXX", "Vanguard Cash Reserves Federal Money Market Fd;Adm", 1.0) 
        );


    @Autowired
    private RestTemplate restTemplate;

    public List<MutualFund> getAllMutualFunds() {
        mutualFunds.forEach(fund -> {
            double beta = getBetaFromNewtonApi(fund.getTicker());
            fund.setBeta(beta);
        });
        return mutualFunds;
    }

    public MutualFund getMutualFundByTicker(String ticker) {
        return mutualFunds.stream()
            .filter(fund -> fund.getTicker().equalsIgnoreCase(ticker))
            .findFirst()
            .orElse(null);
    }

    private double getBetaFromNewtonApi(String ticker) {
        try {
            String url = "https://api.newtonanalytics.com/stock-beta/?ticker=" + ticker + "&index=^GSPC&interval=1mo&observations=12";
            BetaResponse response = restTemplate.getForObject(url, BetaResponse.class);
            return response != null ? response.getBeta() : 1.0;
        } catch (Exception e) {
            System.err.println("Error fetching beta for " + ticker + ": " + e.getMessage());
            return 1.0;
        }
    }
}
