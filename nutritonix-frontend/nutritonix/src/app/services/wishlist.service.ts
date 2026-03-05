import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class WishlistService {

  private apiUrl = 'http://localhost:8080/wishlist';

  constructor(private http: HttpClient) {}

  private getAuthToken(): string | null {
    return localStorage.getItem('token');
  }

  private createHeaders(): HttpHeaders {
    const token = this.getAuthToken();
    return new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: token ? `Bearer ${token}` : '',  // Attach token to headers if available
    });
  }


  addToWishlist(userId: string, tagId: string, query: string): Observable<any> {
    const url = `${this.apiUrl}/add/${userId}/${tagId}`;
    const body = { userId, tagId, query };
    return this.http.post<any>(url, body, { headers: this.createHeaders() });
  }
  

  getWishlist(userId: string): Observable<any[]> {
    const url = `${this.apiUrl}/${userId}`;
    return this.http.get<any[]>(url, { headers: this.createHeaders() });
  }

  removeFromWishlist(userId: string, tagId: string): Observable<void> {
    const url = `${this.apiUrl}/remove/${userId}/${tagId}`;
    return this.http.delete<void>(url, { headers: this.createHeaders() });
  }
}
