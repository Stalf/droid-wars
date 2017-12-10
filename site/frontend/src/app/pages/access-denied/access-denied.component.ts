import {Component} from '@angular/core';
import {AuthService} from '../../services/auth.service';
import {LoginComponent} from '../login/login.component';
import {BsModalService} from 'ngx-bootstrap';

@Component({
    templateUrl: './access-denied.component.html',
    styleUrls: ['./access-denied.component.scss']
})
export class AccessDeniedComponent {

    constructor(private modalService: BsModalService,
                private authService: AuthService) {
    }

    loginModal() {
        this.modalService.show(LoginComponent, {class: 'modal-md'});
    }

}
