import {Component} from '@angular/core';
import {AuthService} from '../../services/auth.service';
import {ActivatedRoute, Router} from '@angular/router';
import {BsModalRef} from 'ngx-bootstrap';

@Component({
  templateUrl: './login.component.html'
})
export class LoginComponent {
    model: any = {};
    loading = false;
    returnUrl: string;

    constructor(
        private modalRef: BsModalRef,
        private route: ActivatedRoute,
        private router: Router,
        private authService: AuthService) { }

    login() {
        this.loading = true;
        this.authService.login(this.model.username, this.model.password)
            .subscribe(
                data => {
                    console.log(this.authService.currentUser);
                },
                error => {
                    console.log(error);
                    this.loading = false;
                });
    }
}
