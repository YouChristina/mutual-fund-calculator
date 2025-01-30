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
  mutualFunds: { ticker: string; name: string }[] = [];
  selectedTicker: string = '';
  initialInvestment: number | null = null;
  timeHorizon: number | null = null;
  futureValue: number | null = null;

  constructor(private calculatorService: CalculatorService) {}

  ngOnInit() {
    console.log('Initializing AppComponent...');
    this.loadMutualFunds();
  }

  loadMutualFunds() {
    console.log('Loading mutual funds...');
    this.calculatorService.getMutualFunds().subscribe(
      data => {
        console.log('Fetched mutual funds:', data);
        if (data.length === 0) {
          console.warn('No mutual funds found!');
        }
        this.mutualFunds = data;
      },
      error => {
        console.error('Error fetching mutual funds:', error);
      }
    );
  }


  calculateFutureValue(): void { // ✅ Added missing function
    if (!this.selectedTicker || !this.initialInvestment || !this.timeHorizon) {
      alert('Please fill in all fields.');
      return;
    }

    const rateOfReturn = 0.1;
    this.futureValue = this.initialInvestment * Math.pow(1 + rateOfReturn, this.timeHorizon);
    console.log(`Calculated Future Value: ${this.futureValue}`);
  }

  openModal() {
    document.getElementById('instructionModal')!.style.display = 'flex';
  }

  closeModal() {
    document.getElementById('instructionModal')!.style.display = 'none';
  }
}
