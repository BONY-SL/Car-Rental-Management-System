import {Component, OnInit} from '@angular/core';
import {AdminService} from "../../service/admin.service";
import {NzMessageService} from "ng-zorro-antd/message";

@Component({
  selector: 'app-get-bookings',
  templateUrl: './get-bookings.component.html',
  styleUrl: './get-bookings.component.css'
})
export class GetBookingsComponent implements OnInit{
  isSpinning: boolean = false;
  bookings: any;

  constructor(private adminService:AdminService,
              private message:NzMessageService) {


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

    this.isSpinning = true;
    this.adminService.changeBookingStatus(id,status).subscribe((res)=>{
        this.isSpinning = false;
        this.getBookingCarsByCustomer();
        this.message.success("Booking Status Changed Successfully!",{nzDuration:5000})
      },error => {
      this.isSpinning = false;
        this.message.error("Booking Status Not Changed !  Something Wrong.... "+error,{nzDuration:5000})

    })
  }
}
