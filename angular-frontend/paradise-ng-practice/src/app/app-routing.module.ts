import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AppMainComponent} from "./app.main.component";
import {DashboardDemoComponent} from "./demo/view/dashboarddemo.component";

const routes: Routes = [
  {
    path: '', component: AppMainComponent,
    children: [
      {path: '', component: DashboardDemoComponent},]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
