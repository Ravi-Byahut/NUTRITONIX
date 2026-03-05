import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserCredential } from '../model/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiUrl = 'http://localhost:8080/user/';
  private http: HttpClient;
  constructor(http: HttpClient) {
    this.http = http;
  }
  getUserById(userId: number, token: string): Observable<UserCredential> {
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    return this.http.get<UserCredential>(`${this.apiUrl}${userId}`, { headers });
  }
}
