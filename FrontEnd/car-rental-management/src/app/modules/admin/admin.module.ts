import { NgModule } from '@angular/core';
import {CommonModule} from '@angular/common';
import { AdminDashboardComponent } from './components/admin-dashboard/admin-dashboard.component';
import {RouterModule, Routes} from "@angular/router";
import { PostCarComponent } from './components/post-car/post-car.component';
import {NgZorroImportsModule} from "../../NgZorroImportsModule";
import {ReactiveFormsModule} from "@angular/forms";
import {NzDatePickerComponent} from "ng-zorro-antd/date-picker";
import { UpdateCarComponent } from './components/update-car/update-car.component';


const routes: Routes = [
  {path : "dashboard",component:AdminDashboardComponent},
  {path : "car",component:PostCarComponent},
  {path : "car/:id",component:UpdateCarComponent}
];

@NgModule({
  declarations: [
    AdminDashboardComponent,
    PostCarComponent,
    UpdateCarComponent
  ],
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    NgZorroImportsModule,
    ReactiveFormsModule,
    NzDatePickerComponent,
  ],
  exports:[
    RouterModule
  ]
})
export class AdminModule{}
