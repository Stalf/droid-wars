import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/observable/of';
import 'rxjs/add/operator/delay';
import 'rxjs/add/operator/do';

@Injectable()
export class AuthService {

    currentUser = null;

    // store the URL so we can redirect after logging in
    redirectUrl: string;

    login(username, password): Observable<boolean> {
        return Observable.of(true).delay(1000).do(val => this.currentUser = {username: "admin"});
    }

    logout(): void {
        this.currentUser = null;
    }

}
