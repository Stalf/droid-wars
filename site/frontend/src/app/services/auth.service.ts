import {Injectable} from '@angular/core';
import 'rxjs/add/observable/of';
import 'rxjs/add/operator/delay';
import 'rxjs/add/operator/do';
import {Http, Response} from '@angular/http';
import {BsModalService} from 'ngx-bootstrap';
import {User} from '../common/models/user';

@Injectable()
export class AuthService {

    currentUser: User = null;

    constructor(private http: Http,
                private modalService: BsModalService) {
    }

    isAuthenticated(): boolean {
        return !!this.currentUser;
    }

    isAnonymous(): boolean {
        return !this.currentUser;
    }

    userName(): string {
        return !!this.currentUser ? this.currentUser.username : 'Guest';
    }

    login(username, password) {
        return this.http.post('/api/authenticate', JSON.stringify({username: username, password: password}))
            .map((response: Response) => {
            // login successful if there's a jwt token in the response
            const user = response.json();
            if (user) {
                this.currentUser = user;
            }
        });
    }

    logout(): void {
         this.currentUser = null;
    }

}
