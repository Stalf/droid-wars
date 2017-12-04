import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, CanLoad, Route, Router, RouterStateSnapshot} from '@angular/router';
import {AuthService} from './auth.service';

@Injectable()
export class AuthGuardService implements CanActivate, CanLoad {

    canLoad(route: Route): boolean {
        return this.checkLogin(route.path);
    }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
        return this.checkLogin(state.url);
    }

    constructor(private authService: AuthService, private router: Router) {
    }


    private checkLogin(url: string): boolean {
       if (this.authService.currentUser) {return true;}

        // Navigate to the login page with extras
        this.router.navigate(['/login'], {queryParams: {returnUrl: url}});
        return false;
    }

}
