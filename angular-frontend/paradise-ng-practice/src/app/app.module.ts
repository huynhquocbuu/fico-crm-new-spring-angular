import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {FormsModule} from "@angular/forms";
import {AppMainComponent} from "./app.main.component";
import {DashboardDemoComponent} from "./demo/view/dashboarddemo.component";
import {HashLocationStrategy, LocationStrategy} from "@angular/common";
import {MenuService} from "./app.menu.service";
import {ProductService} from "./demo/service/productservice";
import {AppTopBarComponent} from "./app.topbar.component";
import {AppMenuComponent} from "./app.menu.component";
import {AppFooterComponent} from "./app.footer.component";
import {AppConfigComponent} from "./app.config.component";
import {AppMenuitemComponent} from "./app.menuitem.component";
import {HttpClientModule} from "@angular/common/http";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {RadioButtonModule} from "primeng/radiobutton";
import {InputSwitchModule} from "primeng/inputswitch";

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
    AppMainComponent,
    AppTopBarComponent,
    AppMenuComponent,
    AppMenuitemComponent,
    AppFooterComponent,
    AppConfigComponent,
    DashboardDemoComponent,
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
