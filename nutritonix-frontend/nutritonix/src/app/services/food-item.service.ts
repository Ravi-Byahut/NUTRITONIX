import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FoodItemService {
  private apiUrl = 'http://localhost:8080/nutrition';

  constructor(private http: HttpClient) {}

  
  private getAuthToken(): string | null {
    return localStorage.getItem('token'); 
  }

  
  private createHeaders(): HttpHeaders {
    const token = this.getAuthToken();
    return new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: token ? `Bearer ${token}` : '', 
    });
  }

  
  searchFood(query: string): Observable<any> {
    const headers = this.createHeaders();
    return this.http.get<any>(`${this.apiUrl}/search/instant?query=${query}`, { headers });
  }

  
  getFoodDetails(nixItemId: string): Observable<any> {
    const headers = this.createHeaders();
    return this.http.get<any>(`${this.apiUrl}/search/item?nixItemId=${nixItemId}`, { headers });
  }

  getNutrientInfo(requestBody: any): Observable<any> {
    const headers = this.createHeaders();
    return this.http.post<any>(`${this.apiUrl}/natural/nutrients`, requestBody, { headers });
  }

  getExerciseInfo(requestBody: any): Observable<any> {
    const headers = this.createHeaders();
    return this.http.post<any>(`${this.apiUrl}/natural/exercise`, requestBody, { headers });
  }

}