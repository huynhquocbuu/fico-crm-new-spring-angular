import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {FormsModule} from "@angular/forms";
import {DashboardDemoComponent} from "./demo/view/dashboarddemo.component";
import {HashLocationStrategy, LocationStrategy} from "@angular/common";
import {ProductService} from "./demo/service/productservice";
import {TopbarComponent} from "./shared/components/topbar/topbar.component";
import {MenuComponent} from "./shared/components/menu/menu.component";
import {FooterComponent} from "./shared/components/footer/footer.component";
import {ConfigComponent} from "./shared/components/theme-config/config.component";
import {MenuItemComponent} from "./shared/components/menu-item/menu-item.component";
import {HttpClientModule} from "@angular/common/http";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {RadioButtonModule} from "primeng/radiobutton";
import {InputSwitchModule} from "primeng/inputswitch";
import {MenuService} from "./shared/services/menu.service";
import {MainComponent} from "./shared/components/main/main.component";
import { AdminDashboardComponent } from './dashboard/admin-dashboard/admin-dashboard.component';
import {LoginComponent} from "./auth/login/login.component";

@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    RadioButtonModule,
    InputSwitchModule,
  ],
  declarations: [
    AppComponent,
    MainComponent,
    TopbarComponent,
    MenuComponent,
    MenuItemComponent,
    FooterComponent,
    ConfigComponent,
    DashboardDemoComponent,
    AdminDashboardComponent,
    LoginComponent
  ],
  providers: [
    {provide: LocationStrategy, useClass: HashLocationStrategy},
    //CountryService, CustomerService, EventService, IconService, NodeService,
    //PhotoService,
    ProductService, MenuService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
