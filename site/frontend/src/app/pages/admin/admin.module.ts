import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ManageUsersComponent} from './manage-users/manage-users.component';
import {AdminDashboardComponent} from './admin-dashboard/admin-dashboard.component';
import {AdminRoutingModule} from './admin-routing.module';
import {AdminComponent} from './admin.component';

@NgModule({
    imports: [
        CommonModule,
        AdminRoutingModule
    ],
    declarations: [
        AdminComponent,
        ManageUsersComponent,
        AdminDashboardComponent
    ]
})
export class AdminModule {
}
