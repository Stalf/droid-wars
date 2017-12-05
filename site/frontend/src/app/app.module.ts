import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {BsDropdownModule} from 'ngx-bootstrap/dropdown';
import {ModalModule} from 'ngx-bootstrap/modal';

import {AppComponent} from './app.component';
import {PageNotFoundComponent} from './common/page-not-found/page-not-found.component';
import {StatisticsComponent} from './pages/statistics/statistics.component';
import {MainComponent} from './pages/main/main.component';
import {FaqComponent} from './pages/faq/faq.component';
import {TopMenuComponent} from './common/top-menu/top-menu.component';
import {LoginComponent} from './pages/login/login.component';
import {AppRoutingModule} from './app-routing.module';
import {AuthGuardService} from './services/auth-guard.service';
import {AuthService} from './services/auth.service';
import {FormsModule} from '@angular/forms';
import {BaseRequestOptions, HttpModule} from '@angular/http';
import {MockBackend} from '@angular/http/testing';
import {fakeBackendProvider} from './common/helpers/fake-backend';
import {RegisterComponent} from './pages/register/register.component';
import {UserService} from './services/user.service';

@NgModule({
    declarations: [
        AppComponent,
        PageNotFoundComponent,
        StatisticsComponent,
        MainComponent,
        FaqComponent,
        TopMenuComponent,
        LoginComponent,
        RegisterComponent
    ],
    imports: [
        BrowserModule,
        HttpModule,
        BsDropdownModule.forRoot(),
        ModalModule.forRoot(),
        FormsModule,
        AppRoutingModule
    ],
    providers: [AuthGuardService,
        AuthService,
        UserService,
        // providers used to create fake backend
        fakeBackendProvider,
        MockBackend,
        BaseRequestOptions],
    bootstrap: [AppComponent],
    entryComponents: [
        LoginComponent
    ]
})
export class AppModule {

}
