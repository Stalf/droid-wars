import {BaseRequestOptions, Http, RequestMethod, Response, ResponseOptions, XHRBackend} from '@angular/http';
import {MockBackend, MockConnection} from '@angular/http/testing';

export function fakeBackendFactory(backend: MockBackend, options: BaseRequestOptions, realBackend: XHRBackend) {

    // array in local storage for registered users
    let users: any[] = JSON.parse(localStorage.getItem('users')) || [];

    let authenticate: Function = (connection: MockConnection) => {

        // authenticate
        if (connection.request.url.endsWith('/api/authenticate') && connection.request.method === RequestMethod.Post) {
            // get parameters from post request
            let params = JSON.parse(connection.request.getBody());

            // find if any user matches login credentials
            let filteredUsers = users.filter(user => {
                return user.username === params.username && user.password === params.password;
            });

            if (filteredUsers.length) {
                // if login details are valid return 200 OK with user details and fake jwt token
                let user = filteredUsers[0];
                connection.mockRespond(new Response(new ResponseOptions({
                    status: 200,
                    body: {
                        id: user.id,
                        username: user.username,
                        email: user.email
                    }
                })));
            } else {
                // else return 400 bad request
                connection.mockError(new Error('Username or password is incorrect'));
            }

            return;
        }
    };

    // configure fake backend
    backend.connections.subscribe((connection: MockConnection) => {
        // wrap in timeout to simulate server api call
        setTimeout(() => {

            authenticate(connection);

        }, 500);
    });

    return new Http(backend, options);

}

export let fakeBackendProvider = {
    // use fake backend in place of Http service for backend-less development
    provide: Http,
    useFactory: fakeBackendFactory,
    deps: [MockBackend, BaseRequestOptions, XHRBackend]
};
