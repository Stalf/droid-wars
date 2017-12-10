import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {AuthService} from '../../services/auth.service';

@Injectable()
export class AdminGuardService implements CanActivate {

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
        const pathArray = state.url.split('/');
        if (pathArray.indexOf('admin') === 0) {
            return this.checkAdmin();
        } else {
            return this.checkAuthenticated();
        }
    }

    constructor(private authService: AuthService, private router: Router) {
    }


    private checkAuthenticated(): boolean {
        if (this.authService.isAuthenticated()) {
            return true;
        }

        this.router.navigate(['/access-denied']);
        return false;
    }

    private checkAdmin() {
        if (this.authService.isAuthenticated()) {
            return this.authService.isAdmin();
        }

        this.router.navigate(['/access-denied']);
        return false;
    }
}
