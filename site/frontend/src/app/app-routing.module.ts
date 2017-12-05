import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {StatisticsComponent} from './pages/statistics/statistics.component';
import {PageNotFoundComponent} from './common/page-not-found/page-not-found.component';
import {FaqComponent} from './pages/faq/faq.component';
import {MainComponent} from './pages/main/main.component';
import {AuthGuardService} from './services/auth-guard.service';
import {RegisterComponent} from './pages/register/register.component';

const appRoutes: Routes = [
    {
        path: 'main', component: MainComponent
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
        path: 'admin',
        canLoad: [AuthGuardService],
        loadChildren: 'app/pages/admin/admin.module#AdminModule',
    },
    {
        path: '',
        redirectTo: '/main',
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
