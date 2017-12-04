import {Component} from '@angular/core';
import {BsModalRef, BsModalService} from 'ngx-bootstrap';
import {LoginComponent} from '../auth/login/login.component';

@Component({
    selector: 'app-top-menu',
    styleUrls: ['./top-menu.component.scss'],
    templateUrl: './top-menu.component.html'
})
export class TopMenuComponent {

    modalRef: BsModalRef;

    constructor(private modalService: BsModalService) {
    }

    loginModal() {
        this.modalRef = this.modalService.show(LoginComponent, {class: "modal-md"});
    }
}
