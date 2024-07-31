import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CustomerDashboardComponent } from './component/customer-dashboard/customer-dashboard.component';
import {RouterModule, Routes} from "@angular/router";
import {NgZorroImportsModule} from "../../NgZorroImportsModule";
import { BookCarComponent } from './component/book-car/book-car.component';
import {ReactiveFormsModule} from "@angular/forms";
import {NzDatePickerComponent} from "ng-zorro-antd/date-picker";

const routes: Routes = [
  {path : "dashboard",component:CustomerDashboardComponent},
  {path : "bookCar/:id",component:BookCarComponent}
];

@NgModule({
  declarations: [
    CustomerDashboardComponent,
    BookCarComponent
  ],
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    NgZorroImportsModule,
    ReactiveFormsModule,
    NzDatePickerComponent
  ],
  exports:[
    RouterModule
  ]
})
export class CustomerModule { }
