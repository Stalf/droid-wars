import {AdminComponent} from './admin.component';
import {RouterModule, Routes} from '@angular/router';
import {NgModule} from '@angular/core';
import {ManageUsersComponent} from './manage-users/manage-users.component';
import {AdminDashboardComponent} from './admin-dashboard/admin-dashboard.component';

const adminRoutes: Routes = [
    {
        path: '', component: AdminComponent,
        children: [
            {
                path: '',
                children: [
                    {path: 'users', component: ManageUsersComponent},
                    {path: '', component: AdminDashboardComponent}
                ]
            }
        ]

    }
];

@NgModule({
    imports: [
        RouterModule.forChild(adminRoutes)
    ],
    exports: [
        RouterModule
    ]
})
export class AdminRoutingModule {}
