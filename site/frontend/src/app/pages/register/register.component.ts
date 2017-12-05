import {Component} from '@angular/core';
import {UserService} from '../../services/user.service';
import {User} from '../../common/models/user';

@Component({
    templateUrl: './register.component.html',
    styleUrls: ['./register.component.scss']
})
export class RegisterComponent {
    model: User = new User();
    loading = false;

    constructor(private userService: UserService) {
    }

    register() {
        this.loading = true;
        this.userService.create(this.model)
            .subscribe(
                data => {
                    console.log(data);
                },
                 error => {
                    console.log(error);
                    this.loading = false;
                 }
            )
    }



}
