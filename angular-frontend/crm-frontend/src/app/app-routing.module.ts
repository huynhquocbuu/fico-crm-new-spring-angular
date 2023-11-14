import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {MainComponent} from "./shared/components/main/main.component";
import {DashboardDemoComponent} from "./demo/view/dashboarddemo.component";
import {AdminDashboardComponent} from "./dashboard/admin-dashboard/admin-dashboard.component";
import {LoginComponent} from "./auth/login/login.component";

const routes: Routes = [
  {
    path: '', component: MainComponent,
    children: [
      {path: '', component: AdminDashboardComponent},
      {path: 'demo', component: DashboardDemoComponent},



    ]
  },
  {path: 'login', component: LoginComponent},
  {path: '**', redirectTo: '/notfound'},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
