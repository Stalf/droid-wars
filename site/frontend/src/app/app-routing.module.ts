import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {StatisticsComponent} from './pages/statistics/statistics.component';
import {PageNotFoundComponent} from './pages/page-not-found/page-not-found.component';
import {FaqComponent} from './pages/faq/faq.component';
import {AdminGuardService} from './pages/admin/admin-guard.service';
import {RegisterComponent} from './pages/register/register.component';
import {SuccessfulRegisterComponent} from './pages/successful-register/successful-register.component';
import {SuccessfulRegisterGuardService} from './pages/successful-register/successful-register-guard.service';
import {AccessDeniedComponent} from './pages/access-denied/access-denied.component';
import {HomeComponent} from './pages/home/home.component';

const appRoutes: Routes = [
    {
        path: 'home', component: HomeComponent
    },
    {
        path: 'statistics', component: StatisticsComponent
    },
    {
        path: 'faq', component: FaqComponent
    },
    {
        path: 'register', component: RegisterComponent
    },
    {
        path: 'register-success',
        canActivate: [SuccessfulRegisterGuardService],
        component: SuccessfulRegisterComponent
    },
    {
        path: 'access-denied', component: AccessDeniedComponent
    },
    {
        path: 'admin',
        canActivate: [AdminGuardService],
        loadChildren: 'app/pages/admin/admin.module#AdminModule',
    },
    {
        path: '',
        redirectTo: '/home',
        pathMatch: 'full'
    },
    {
        path: '**', component: PageNotFoundComponent
    }
];


@NgModule({
    imports: [
        RouterModule.forRoot(
            appRoutes
        )
    ],
    declarations: [],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
