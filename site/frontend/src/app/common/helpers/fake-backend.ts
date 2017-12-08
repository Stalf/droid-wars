import {BaseRequestOptions, Http, RequestMethod, Response, ResponseOptions, XHRBackend} from '@angular/http';
import {MockBackend, MockConnection} from '@angular/http/testing';

export function fakeBackendFactory(backend: MockBackend, options: BaseRequestOptions, realBackend: XHRBackend) {

    // array in local storage for registered users
    const users: any[] = JSON.parse(localStorage.getItem('users')) || [];

    const authenticate: Function = (connection: MockConnection) => {

        // authenticate
        if (connection.request.url.endsWith('/api/authenticate') && connection.request.method === RequestMethod.Post) {
            // get parameters from post request
            const params = JSON.parse(connection.request.getBody());

            // find if any user matches login credentials
            const filteredUsers = users.filter(user => {
                return user.username === params.username && user.password === params.password;
            });

            if (filteredUsers.length) {
                // if login details are valid return 200 OK with user details and fake jwt token
                const user = filteredUsers[0];
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

    const createUser: Function = (connection: MockConnection) => {
        // create user
        if (connection.request.url.endsWith('/api/users') && connection.request.method === RequestMethod.Post) {
            // get new user object from post body
            const newUser = JSON.parse(connection.request.getBody());

            // validation
            const duplicateUser = users.filter(user => {
                return user.username === newUser.username;
            }).length;
            if (duplicateUser) {
                return connection.mockError(new Error('Username "' + newUser.username + '" is already taken'));
            }

            // save new user
            newUser.id = users.length + 1;
            users.push(newUser);
            localStorage.setItem('users', JSON.stringify(users));

            // respond 200 OK
            connection.mockRespond(new Response(new ResponseOptions({status: 200})));

            return;
        }
    };

    // configure fake backend
    backend.connections.subscribe((connection: MockConnection) => {
        // wrap in timeout to simulate server api call
        setTimeout(() => {

            authenticate(connection);
            createUser(connection);

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
