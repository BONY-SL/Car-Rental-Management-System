import { Component } from '@angular/core';
import {CustomerService} from "../../service/customer.service";

@Component({
  selector: 'app-my-bookings',
  templateUrl: './my-bookings.component.html',
  styleUrl: './my-bookings.component.css'
})
export class MyBookingsComponent {

  bookings:any;
  isSpinning: boolean = false;

  constructor(private customerService:CustomerService) {

    this.getMyBookings();
  }

  getMyBookings(){
    this.isSpinning =  true;
    this.customerService.getBookingCarByUserId().subscribe((res) =>{
      this.isSpinning =  false;
      this.bookings = res;
    })
  }

  getColor(status: string): string {
    switch(status) {
      case 'APPROVED':
        return 'green';
      case 'REJECTED':
        return 'red';
      case 'PENDING':
        return 'orange';
      default:
        return 'black';
    }
  }


}
