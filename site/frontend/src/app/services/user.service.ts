import {Injectable} from '@angular/core';
import {Http, Response} from '@angular/http';
import {User} from '../common/models/user';

@Injectable()
export class UserService {

    constructor(private http: Http) {
    }

    getAll() {
        return this.http.get('/api/users').map((response: Response) => response.json());
    }

    getById(id: number) {
        return this.http.get('/api/users/' + id).map((response: Response) => response.json());
    }

    create(user: User) {
        return this.http.post('/api/register', user).map((response: Response) => response.json());
    }

    update(user: User) {
        return this.http.put('/api/users/' + user.id, user).map((response: Response) => response.json());
    }

    delete(id: number) {
        return this.http.delete('/api/users/' + id).map((response: Response) => response.json());
    }

}
