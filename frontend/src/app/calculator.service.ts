import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CalculatorService {
  private mutualFundsApiUrl = 'http://localhost:8080/api/mutualfunds';

  constructor(private http: HttpClient) { }

  getMutualFunds(): Observable<{ ticker: string; name: string }[]> {
    return this.http.get<{ ticker: string; name: string }[]>(this.mutualFundsApiUrl);
  }
}
