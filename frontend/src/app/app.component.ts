import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { CalculatorService } from './calculator.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  standalone: true,
  imports: [
    CommonModule,
    FormsModule
  ]
})
export class AppComponent {
  title = 'Mutual Fund Calculator';
  mutualFunds: { ticker: string; name: string; beta: number }[] = [];
  selectedTicker: string = '';
  initialInvestment: number = 0;
  timeHorizon: number = 0;
  futureValue: number | null = null;

  constructor(private calculatorService: CalculatorService) {}

  ngOnInit() {
    console.log('Initializing AppComponent...');
    this.loadMutualFunds();
  }

  loadMutualFunds() {
    this.calculatorService.getMutualFunds().subscribe(data => {
      console.log("Fetched mutual funds:", data);
      this.mutualFunds = data;
    }, error => {
      console.error("Error fetching mutual funds:", error);
    });
  }

  calculateFutureValue(): void {
    if (!this.selectedTicker || !this.initialInvestment || !this.timeHorizon) {
      alert('Please fill in all fields.');
      return;
    }

    const selectedFund = this.mutualFunds.find(fund => fund.ticker === this.selectedTicker);

    if (!selectedFund) {
      alert('Invalid mutual fund selected.');
      return;
    }

    const beta = selectedFund.beta; // Get beta from the mutual fund data

    this.calculatorService.getInvestmentRate().subscribe(({ riskFreeRate, marketReturnRate }) => {
      // Convert rates from percentage to decimal
      const riskFreeDecimal = riskFreeRate / 100;
      const marketReturnDecimal = marketReturnRate / 100;

      // Calculate CAPM return rate
      let rateOfReturn = riskFreeDecimal + beta * (marketReturnDecimal - riskFreeDecimal);

      console.log(`Risk-Free Rate: ${riskFreeDecimal}`);
      console.log(`Market Return Rate: ${marketReturnDecimal}`);
      console.log(`Beta: ${beta}`);
      console.log(`Calculated Rate of Return (r): ${rateOfReturn}`);

      // Ensure r is within a reasonable range
      if (rateOfReturn > 0.5) {
        console.warn("⚠️ Capping high rate of return:", rateOfReturn);
        rateOfReturn = 0.5; // Cap at 50% to avoid unrealistic values
      }

      // Calculate Future Value (FV = P * e^(rt))
      this.futureValue = this.initialInvestment * Math.exp(rateOfReturn * this.timeHorizon);

      console.log(`💰 Calculated Future Value: ${this.futureValue}`);
    });
  }

  // Fix: Add missing modal functions
  openModal() {
    const modal = document.getElementById('instructionModal');
    if (modal) modal.style.display = 'flex';
  }

  closeModal() {
    const modal = document.getElementById('instructionModal');
    if (modal) modal.style.display = 'none';
  }
}
