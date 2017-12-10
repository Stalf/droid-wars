import {Component, OnInit} from '@angular/core';
import {LoginComponent} from '../login/login.component';
import {BsModalService} from 'ngx-bootstrap';
import {ActivatedRoute} from '@angular/router';
import 'rxjs/add/operator/switchMap';

@Component({
    templateUrl: './successful-register.component.html',
    styleUrls: ['./successful-register.component.scss']
})
export class SuccessfulRegisterComponent implements OnInit {
    model: any = {};

    constructor(private modalService: BsModalService,
                private route: ActivatedRoute) {
    }

    ngOnInit(): void {
        this.model.username = this.route.snapshot.params['username'];
        this.model.email = this.route.snapshot.params['email'];
    }

    loginModal(username: string) {
        const bsModalRef = this.modalService.show(LoginComponent, {class: 'modal-md'});
        bsModalRef.content.model.username = username;
    }
}
