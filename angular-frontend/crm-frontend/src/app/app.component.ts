import {Component, OnInit} from '@angular/core';
import {PrimeNGConfig} from "primeng/api";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  //styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  //title = 'crm-frontend';
  menu = 'slim';

  layout = 'default';

  darkMenu = true;

  inputStyle = 'outlined';

  ripple: boolean;
  constructor(private primengConfig: PrimeNGConfig){

  }

  ngOnInit() {
    this.primengConfig.ripple = true;
  }
}
