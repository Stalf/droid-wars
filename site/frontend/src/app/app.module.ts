import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {BsDropdownModule} from 'ngx-bootstrap/dropdown';
import {ModalModule} from 'ngx-bootstrap/modal';

import {AppComponent} from './app.component';
import {PageNotFoundComponent} from './pages/page-not-found/page-not-found.component';
import {StatisticsComponent} from './pages/statistics/statistics.component';
import {FaqComponent} from './pages/faq/faq.component';
import {TopMenuComponent} from './common/top-menu/top-menu.component';
import {LoginComponent} from './pages/login/login.component';
import {AppRoutingModule} from './app-routing.module';
import {AdminGuardService} from './pages/admin/admin-guard.service';
import {AuthService} from './services/auth.service';
import {FormsModule} from '@angular/forms';
import {BaseRequestOptions, HttpModule} from '@angular/http';
import {RegisterComponent} from './pages/register/register.component';
import {UserService} from './services/user.service';
import {SuccessfulRegisterComponent} from './pages/successful-register/successful-register.component';
import {SuccessfulRegisterGuardService} from './pages/successful-register/successful-register-guard.service';
import {AccessDeniedComponent} from './pages/access-denied/access-denied.component';
import {HomeComponent} from './pages/home/home.component';
import {CustomFormsModule} from 'ng2-validation';
import {NativeValidityDirective} from './common/directives/native-validity.directive';

@NgModule({
    declarations: [
        AppComponent,
        PageNotFoundComponent,
        StatisticsComponent,
        FaqComponent,
        TopMenuComponent,
        LoginComponent,
        RegisterComponent,
        SuccessfulRegisterComponent,
        AccessDeniedComponent,
        HomeComponent,
        NativeValidityDirective
    ],
    imports: [
        BrowserModule,
        HttpModule,
        BsDropdownModule.forRoot(),
        ModalModule.forRoot(),
        FormsModule,
        CustomFormsModule,
        AppRoutingModule
    ],
    providers: [AdminGuardService,
        UserService,
        SuccessfulRegisterGuardService,
        AuthService,
        // providers used to create fake backend
        // fakeBackendProvider,
        // MockBackend,
        BaseRequestOptions],
    bootstrap: [AppComponent],
    entryComponents: [
        LoginComponent
    ]
})
export class AppModule {

    constructor(public authService: AuthService) {
    }
}
