import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CustomerDashboardComponent } from './component/customer-dashboard/customer-dashboard.component';
import {RouterModule, Routes} from "@angular/router";
import {NgZorroImportsModule} from "../../NgZorroImportsModule";
import { BookCarComponent } from './component/book-car/book-car.component';
import {ReactiveFormsModule} from "@angular/forms";
import {NzDatePickerComponent} from "ng-zorro-antd/date-picker";
import { MyBookingsComponent } from './component/my-bookings/my-bookings.component';

const routes: Routes = [
  {path : "dashboard",component:CustomerDashboardComponent},
  {path : "bookCar/:id",component:BookCarComponent},
  {path : "my_bookings",component:MyBookingsComponent}
];

@NgModule({
  declarations: [
    CustomerDashboardComponent,
    BookCarComponent,
    MyBookingsComponent
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
