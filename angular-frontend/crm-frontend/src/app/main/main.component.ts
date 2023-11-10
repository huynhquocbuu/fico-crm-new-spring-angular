import { Component } from '@angular/core';
import {AppComponent} from "../app.component";
import {PrimeNGConfig} from "primeng/api";

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  //styleUrls: ['./main.component.scss']
})
export class MainComponent {
    staticMenuInactive: boolean;
    overlayMenuActive: boolean;
    mobileMenuActive: boolean;
    menuClick: boolean;
    menuButtonClick: boolean;
    topbarMenuButtonClick: boolean;
    topbarMenuActive: boolean;
    activeTopbarItem: Element;
    menuHoverActive: boolean;
    configActive: boolean;
    configClick: boolean;

  constructor(private menuService: MenuService, private primengConfig: PrimeNGConfig, public app: AppComponent) {
  }
}
