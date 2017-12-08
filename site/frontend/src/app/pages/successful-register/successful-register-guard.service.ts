import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot} from '@angular/router';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class SuccessfulRegisterGuardService implements CanActivate {

    // Permits navigation to /register-success page only if username param exists
    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
        return !!route.params.get('username');
    }

  constructor() { }

}
