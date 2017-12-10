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
    error: String = null;

    constructor(private userService: UserService,
                private router: Router) {
    }

    register() {
        this.loading = true;
        this.error = null;
        this.userService.create(this.model)
            .subscribe(
                data => {
                    this.router.navigate(['/register-success', {username: this.model.username, email: this.model.email}]);
                },
                error => {
                    const errorBody = JSON.parse(error._body);
                    if (errorBody.message) {
                        this.error = errorBody.message;
                    } else {
                        this.error = error.status;
                    }
                    this.loading = false;
                }
            );
    }


}
