import {Component} from '@angular/core';
import {UserService} from '../../services/user.service';
import {User} from '../../common/models/user';
import {Router} from '@angular/router';

@Component({
    templateUrl: './register.component.html',
    styleUrls: ['./register.component.scss']
})
export class RegisterComponent {
    model: User = new User();
    loading = false;

    constructor(private userService: UserService,
                private router: Router) {
    }

    register() {
        this.loading = true;
        this.userService.create(this.model)
            .subscribe(
                data => {
                    console.log(data);
                    this.router.navigate(['/register-success', {username: this.model.username, email: this.model.email}]);
                },
                error => {
                    console.log(error);
                    this.loading = false;
                }
            );
    }


}
