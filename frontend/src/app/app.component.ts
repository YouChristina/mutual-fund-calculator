
import { Component } from '@angular/core';
import {FormsModule} from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  standalone: true, // Standalone component
  imports: [
    CommonModule,
    FormsModule,
  ],
})
export class AppComponent {
  title = 'Mutual Fund Calculator';
  mutualFunds: string[] = ['Fund A', 'Fund B', 'Fund C']; // Mock mutual funds
  selectedTicker: string = '';
  initialInvestment: number | null = null; // Initial investment
  timeHorizon: number | null = null; // Investment duration (years)
  futureValue: number | null = null; // Predicted future value

  constructor() {}

  calculateFutureValue(): void {
    if (!this.selectedTicker || !this.initialInvestment || !this.timeHorizon) {
      alert('Please fill in all fields.');
      return;
    }

    const rateOfReturn = 0.1; // Mock rate of return (10%)
    this.futureValue = this.initialInvestment * Math.pow(1 + rateOfReturn, this.timeHorizon);
  }
}
