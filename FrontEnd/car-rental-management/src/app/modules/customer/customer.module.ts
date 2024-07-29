import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CustomerDashboardComponent } from './component/customer-dashboard/customer-dashboard.component';
import {RouterModule, Routes} from "@angular/router";

const routes: Routes = [
  {path : "dashboard",component:CustomerDashboardComponent}
];

@NgModule({
  declarations: [
    CustomerDashboardComponent
  ],
  imports: [
    CommonModule,
    RouterModule.forChild(routes)
  ],
  exports:[
    RouterModule
  ]
})
export class CustomerModule { }
