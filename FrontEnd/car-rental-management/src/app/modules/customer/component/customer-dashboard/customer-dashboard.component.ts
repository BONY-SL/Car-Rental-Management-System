import {Component, OnInit} from '@angular/core';
import {CustomerService} from "../../service/customer.service";

@Component({
  selector: 'app-customer-dashboard',
  templateUrl: './customer-dashboard.component.html',
  styleUrl: './customer-dashboard.component.css'
})
export class CustomerDashboardComponent implements OnInit{

  cars: any[] = [];

  constructor(private customerService : CustomerService) {
  }

  ngOnInit() {
    this.getAllCars();
  }

  getAllCars() {
    this.customerService.getAllCars().subscribe((res) => {
      console.log(res);
      res.forEach((element: any) => {
        element.processedImg = 'data:image/jpeg;base64,' + element.returnedImage;
        this.cars.push(element);
      });
    });
  }
}
