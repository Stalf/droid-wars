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
import {LoginComponent} from './common/auth/login/login.component';
import {AppRoutingModule} from './app-routing.module';
import {AuthGuardService} from './common/auth/auth-guard.service';
import {AuthService} from './common/auth/auth.service';
import {FormsModule} from '@angular/forms';

@NgModule({
    declarations: [
        AppComponent,
        PageNotFoundComponent,
        StatisticsComponent,
        MainComponent,
        FaqComponent,
        TopMenuComponent,
        LoginComponent
    ],
    imports: [
        BrowserModule, BsDropdownModule.forRoot(),
        ModalModule.forRoot(),
        FormsModule,
        AppRoutingModule
    ],
    providers: [AuthGuardService, AuthService],
    bootstrap: [AppComponent],
    entryComponents: [
        LoginComponent
    ]
})
export class AppModule {

}
