import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {StatisticsComponent} from './pages/statistics/statistics.component';
import {PageNotFoundComponent} from './common/page-not-found/page-not-found.component';
import {FaqComponent} from './pages/faq/faq.component';
import {MainComponent} from './pages/main/main.component';
import {AuthGuardService} from './common/auth/auth-guard.service';

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
