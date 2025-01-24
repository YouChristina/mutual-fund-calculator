import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CalculatorService {
  private mutualFundsApiUrl = '';
  constructor(private http: HttpClient) { }

  getMutualFunds() {
    let mutualFunds: Array<string>;
    mutualFunds = ['FundA', 'FundB', 'FundC', 'FundD']
    return mutualFunds
  }
}
