import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CalculatorService {
  private mutualFundsApiUrl ='http://localhost:8080/api/mutualfunds';

  //include private http: HttpClient as a parameter to constructor
  constructor(private http: HttpClient) { }

  getMutualFunds(): Observable<any> { // Change return type to Observable
    return this.http.get<any[]>(this.mutualFundsApiUrl);
  }
}
