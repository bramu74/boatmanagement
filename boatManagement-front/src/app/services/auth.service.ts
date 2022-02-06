import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {LocalStorageService} from "./local-storage.service";
import {TokenResult} from "../models/token-result.model";

const baseUrl = 'https://localhost:8080';
const tokenKey = "token";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient,
              private localStorageService : LocalStorageService) {
  }

  login(data: any): Observable<boolean> {
    var formData: any = new FormData();
    formData.append("login", data.login);
    formData.append("password", data.password);
    return new Observable<boolean>((subscriber) => {
    this.http.post<TokenResult>(`${baseUrl}/token`, formData)
    .subscribe({
      next: (res) => {
        if(res.authorizationBearerToken != null) {
          this.localStorageService.set(tokenKey, res.authorizationBearerToken);
          subscriber.next(true);
        }
        subscriber.error("no token");
      },
      error: (e) => {
        subscriber.error(e);
      }
    });
  });
  }

  logout(): Observable<boolean> {
    return new Observable<boolean>((subscriber) => {
    this.http.post(`${baseUrl}/api/logout`, null)
      .subscribe({
        next: (res) => {
          this.invalidateToken();
          subscriber.next(true);
        },
        error: (e) => {
          this.invalidateToken();
          subscriber.error(e);
        }
      });
    });
  }

  isAuthenticated(): boolean {
    return this.getToken() != null;
  }

  getToken(): any {
    return this.localStorageService.get(tokenKey);
  }

  invalidateToken(): void {
    this.localStorageService.remove(tokenKey);
  }
}
