import { Component, OnInit } from '@angular/core';
import { AdminService } from "../../service/admin.service";
import {NzMessageService} from "ng-zorro-antd/message";
import {Router} from "@angular/router";
import Swal from "sweetalert2";

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.css']  // Fix styleUrl to styleUrls
})
export class AdminDashboardComponent implements OnInit {

  cars: any[] = [];

  constructor(private adminService: AdminService,private message:NzMessageService,private router:Router) {
  }

  ngOnInit() {
    this.getAllCars();
  }

  getAllCars() {
    this.adminService.getAllCars().subscribe((res) => {
      console.log(res);
      res.forEach((element: any) => {
        element.processedImg = 'data:image/jpeg;base64,' + element.returnedImage;
        this.cars.push(element);
      });
    });
  }

  deleteCar(id: number) {
    Swal.fire({
      title: 'Are you sure Delete this Car?',
      text: "You won't be able to revert this!",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3c8c3c',
      cancelButtonColor: '#f85555',
      confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
      if (result.isConfirmed) {
        this.adminService.deleteCar(id).subscribe((res) => {
          this.message.success("Car Deleted Successfully", { nzDuration: 5000 });
          this.cars = this.cars.filter(car => car.id !== id);
        }, error => {
          this.message.error("Failed to delete the car");
        });
      }
    });
  }
}
