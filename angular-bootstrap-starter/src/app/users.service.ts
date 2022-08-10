import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { Person } from './model/Person';
import { tap, catchError, map} from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class UsersService {
  private baseURL = `http://localhost:8080/api/person`
  constructor(private http: HttpClient) { }

  getAllData(): Observable<Person[]> {
    return this.http.get<Person[]>(`${this.baseURL}`).pipe(
      tap(data => console.log(data)),
      catchError((err)=> {
        return throwError(err)
      })
    );
  }

  postData(data: Person): Observable<Person> {
    return this.http.post<Person>(`${this.baseURL}`, data)
  }

  updateData(data: Person, id: number): Observable<Person> {
    return this.http.put<Person>(`${this.baseURL}/${id}`, data)
  }

  deleteData(id: number): Observable<any> {
    return this.http.delete(`${this.baseURL}/${id}`)
  }
}
