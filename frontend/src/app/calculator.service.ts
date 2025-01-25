import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
//import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CalculatorService {
  //private mutualFundsApiUrl = 'http://localhost:8080'; -- when service is running

  //include private http: HttpClient as a parameter to constructor
  constructor() { }

  getMutualFunds() {
    //I wasn't able to run the backend server for some reason so i've commented out the code

    let mutualFunds: Array<string>;
    mutualFunds = ['FundA', 'FundB', 'FundC', 'FundD']
    return mutualFunds
  }

  //getFunds(): Observable<string[]> {
    //return this.http.get<string[]>(this.mutualFundsApiUrl);
  //}
}
