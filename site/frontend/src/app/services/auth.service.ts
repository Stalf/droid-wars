import {Injectable} from '@angular/core';
import 'rxjs/add/observable/of';
import 'rxjs/add/operator/delay';
import 'rxjs/add/operator/do';
import {Http, Response} from '@angular/http';

@Injectable()
export class AuthService {

    currentUser = null;

    constructor(private http: Http) {
    }

    login(username, password) {
        return this.http.post('/api/authenticate', JSON.stringify({username: username, pass: password}))
            .map((response: Response) => {
            // login successful if there's a jwt token in the response
            let user = response.json();
            if (user) {
                this.currentUser = user;
            }
        });
    }

    logout(): void {
        this.currentUser = null;
    }

}
