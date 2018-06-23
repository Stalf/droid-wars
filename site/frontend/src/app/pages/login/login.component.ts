import {Component} from '@angular/core';
import {AuthService} from '../../services/auth.service';
import {ActivatedRoute, Router} from '@angular/router';
import {BsModalRef} from 'ngx-bootstrap';

@Component({
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss']
})
export class LoginComponent {
    model: any = {};
    loading = false;
    error: String = null;

    constructor(private modalRef: BsModalRef,
                private route: ActivatedRoute,
                private router: Router,
                private authService: AuthService) {
    }

    login() {
        this.loading = true;
        this.error = null;
        this.authService.login(this.model.username, this.model.password)
            .subscribe(
                data => {
                    this.modalRef.hide();
                    this.router.navigate(['/home']);
                },
                error => {
                    const errorBody = JSON.parse(error._body);
                    if (errorBody.message) {
                        this.error = errorBody.message;
                    } else {
                        this.error = error.status;
                    }
                    this.loading = false;
                });
    }
}
