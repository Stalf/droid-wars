import {Injectable} from '@angular/core';
import 'rxjs/add/observable/of';
import 'rxjs/add/operator/delay';
import 'rxjs/add/operator/do';
import {Headers, Http, RequestOptions, Response} from '@angular/http';
import {User} from '../common/models/user';
import {Router} from '@angular/router';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class AuthService {

    private currentUser: User | Observable<User> = null;
    private token: String = null;

    constructor(private http: Http,
                private router: Router) {
    }

    /**
     * @returns {String} JWT token cached in AuthService.token
     */
    getToken(): String {
        if (this.token) {
            return this.token;
        } else {
            const storageCurrentUser = JSON.parse(localStorage.getItem('currentUser'));
            this.token = storageCurrentUser && storageCurrentUser.token ? storageCurrentUser.token : '';
            return this.token;
        }
    }

    getCurrentUser(): User {
        if (this.currentUser && !(this.currentUser instanceof Observable)) {
            return this.currentUser;
        } else {
            return null;
        }
    }

    isAuthenticated(): boolean {
        return !!this.getCurrentUser();
    }

    isAnonymous(): boolean {
        return !this.getCurrentUser();
    }

    isAdmin(): boolean {
        // TODO very simple role check
        return this.isAuthenticated() ? this.getCurrentUser().username === 'admin' : false;
    }

    getUserName(): string {
        return this.isAuthenticated() ? this.getCurrentUser().username : 'Guest';
    }

    login(username, password): Observable<boolean> {
        const headers = new Headers({'Content-Type': 'application/json'});
        const options = new RequestOptions({headers: headers});
        return this.http.post('/api/authenticate', JSON.stringify({username: username, password: password}), options)
            .map((response: Response) => {
                // login successful if there's a jwt token in the response
                const token = response.json() && response.json().token;
                if (token) {
                    // store jwt token in local storage to keep user logged in between page refreshes
                    localStorage.setItem('currentUser', JSON.stringify({token: token}));

                    this.requestCurrentUser(token);

                    // return true to indicate successful login
                    return true;
                } else {
                    // return false to indicate failed login
                    return false;
                }
            });
    }

    private requestCurrentUser(token: any) {
        const headers = new Headers({
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + token
        });

        const options = new RequestOptions({headers: headers});
        // Request current user data from backend
        this.currentUser = this.http.get('/api/current-user', options).map((res: Response) => {
            const data = res.json();
            if (data.principal) {
                return data.principal;
            } else {
                return Observable.throw(new Error('Error retrieving principal'));
            }
        });
        this.currentUser.subscribe(
            (data: User) => {
                this.currentUser = data;
            },
            error => {
                // If data can`t be retrieved - logout the user
                this.logout();
            }
        );
    }

    logout(): void {
        // remove user from local storage to log user out
        localStorage.removeItem('currentUser');
        this.currentUser = null;
        this.router.navigate(['home']);
    }

}
