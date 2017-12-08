import {Component} from '@angular/core';
import {BsModalService} from 'ngx-bootstrap';
import {LoginComponent} from '../../pages/login/login.component';
import {AuthService} from '../../services/auth.service';

@Component({
    selector: 'app-top-menu',
    styleUrls: ['./top-menu.component.scss'],
    templateUrl: './top-menu.component.html'
})
export class TopMenuComponent {

    constructor(private modalService: BsModalService,
                private authService: AuthService) {
    }

    loginModal() {
        this.modalService.show(LoginComponent, {class: 'modal-md'});
    }
}
