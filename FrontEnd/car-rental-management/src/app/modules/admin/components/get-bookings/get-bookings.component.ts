import {Component, OnInit} from '@angular/core';
import {AdminService} from "../../service/admin.service";

@Component({
  selector: 'app-get-bookings',
  templateUrl: './get-bookings.component.html',
  styleUrl: './get-bookings.component.css'
})
export class GetBookingsComponent implements OnInit{
  isSpinning: boolean = false;
  bookings: any;

  constructor(private adminService:AdminService) {


  }

  ngOnInit() {

    this.getBookingCarsByCustomer();
  }

  getBookingCarsByCustomer(){
    this.isSpinning = true;
    this.adminService.getBookingCarsByCustomer().subscribe((res) => {
      this.isSpinning = false;
      this.bookings = res;
    });
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

  changeBookingStatus(id:number, status: string) {

  }
}
