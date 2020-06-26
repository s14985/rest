import { Injectable } from '@angular/core';
import { User } from '../models/user.model';
import { Subject } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class AuthenticationService {
  private currentUser: User;
  private _currentUserSubject = new Subject();
  CurrentUserChanged$ = this._currentUserSubject.asObservable();
  private loginUrl: string = 'http://localhost:8082/login';

  constructor(private httpClient: HttpClient) {}

  getCurrentUser() {
    return this.currentUser;
  }

  setCurrentUser(value: User) {
    this.currentUser = value;
    this._currentUserSubject.next();
  }

  login(email: string, password: string) {
    const formData = new FormData();
    formData.append('username', email);
    formData.append('password', password);

    return this.httpClient
      .post<any>(this.loginUrl, formData, { withCredentials: true })
      .pipe(
        tap(
          (data) => {
            return data;
          },
          (error) => {
            return error;
          }
        )
      );
  }
}
