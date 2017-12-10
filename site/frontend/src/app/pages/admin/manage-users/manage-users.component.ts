import {Component, OnInit} from '@angular/core';
import {UserService} from '../../../services/user.service';
import {User} from '../../../common/models/user';

@Component({
    templateUrl: './manage-users.component.html'
})
export class ManageUsersComponent implements OnInit {

    users: User[] = [];

    constructor(private userService: UserService) {
    }

    ngOnInit() {
        this.loadAllUsers();
    }

    private loadAllUsers() {
        this.userService.getAll().subscribe(users => {
            this.users = users;
        });
    }

}
