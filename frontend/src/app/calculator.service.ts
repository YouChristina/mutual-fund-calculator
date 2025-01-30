import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, forkJoin } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class CalculatorService {
  private mutualFundsApiUrl = 'http://localhost:8080/api/mutualfunds';
  private riskFreeRateUrl = '/api/stlouis/fred/series/observations?series_id=DGS10&api_key=d26079fc190512773ac705629a92f8ea&file_type=json';
  private marketReturnUrl = '/api/stlouis/fred/series/observations?series_id=SP500&api_key=d26079fc190512773ac705629a92f8ea&file_type=json';

  constructor(private http: HttpClient) {}

  getMutualFunds(): Observable<{ ticker: string; name: string; beta: number }[]> {
    return this.http.get<{ ticker: string; name: string; beta: number }[]>(this.mutualFundsApiUrl);
  }

  getRiskFreeRate(): Observable<number> {
    return this.http.get<any>(this.riskFreeRateUrl).pipe(
      map(response => {
        const observations = response.observations;
        return observations.length > 0 ? parseFloat(observations[observations.length - 1].value) : 0;
      })
    );
  }

  getMarketReturnRate(): Observable<number> {
    return this.http.get<any>(this.marketReturnUrl).pipe(
      map(response => {
        const observations: { date: string; value: string }[] = response.observations;

        if (!observations || observations.length < 2) {
          console.error("Market return data missing or insufficient");
          return NaN;
        }

        // Extract first and last trading day of the previous year
        const firstEntry = observations.find((o: { date: string }) => o.date.includes('-01-')) || observations[0];
        const lastEntry = observations[observations.length - 1];

        if (!firstEntry || !lastEntry || firstEntry.value === "." || lastEntry.value === ".") {
          console.error("Market return API returned invalid data");
          return NaN;
        }

        const firstDay = parseFloat(firstEntry.value);
        const lastDay = parseFloat(lastEntry.value);

        if (isNaN(firstDay) || isNaN(lastDay) || firstDay === 0) {
          console.error("Invalid market return values:", { firstDay, lastDay });
          return NaN;
        }

        const marketReturnRate = (lastDay - firstDay) / firstDay;
        console.log("Market Return Rate (SP500):", marketReturnRate);
        return marketReturnRate;
      })
    );
  }


  getInvestmentRate(): Observable<{ riskFreeRate: number; marketReturnRate: number }> {
    return forkJoin({
      riskFreeRate: this.getRiskFreeRate(),
      marketReturnRate: this.getMarketReturnRate()
    });
  }
}
