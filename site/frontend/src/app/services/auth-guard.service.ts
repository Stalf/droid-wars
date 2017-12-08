import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, CanLoad, Route, Router, RouterStateSnapshot} from '@angular/router';
import {AuthService} from './auth.service';
import {LoginComponent} from '../pages/login/login.component';
import {BsModalService} from 'ngx-bootstrap';

@Injectable()
export class AuthGuardService implements CanActivate, CanLoad {

    canLoad(route: Route): boolean {
        return this.checkLogin(route.path);
    }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
        return this.checkLogin(state.url);
    }

    constructor(private authService: AuthService, private router: Router,
                private modalService: BsModalService) {
    }


    private checkLogin(url: string): boolean {
        if (this.authService.currentUser) {
            return true;
        }

        this.modalService.show(LoginComponent, {class: 'modal-md'});
        return false;
    }

}
