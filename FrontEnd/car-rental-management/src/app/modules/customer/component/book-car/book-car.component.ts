import {Component, OnInit} from '@angular/core';
import {NzMessageService} from "ng-zorro-antd/message";
import {ActivatedRoute, Router} from "@angular/router";
import {CustomerService} from "../../service/customer.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {StorageService} from "../../../../auth/services/storage/storage.service";

@Component({
  selector: 'app-book-car',
  templateUrl: './book-car.component.html',
  styleUrl: './book-car.component.css'
})
export class BookCarComponent implements OnInit{

  id:number =  this.activatedRoute.snapshot.params["id"];

  car:any;

  processedImage:any;

  validateForm!:FormGroup;
  isSpinning: boolean = false;
  dateFormat!:"DD-MM-YYYY";


  constructor(private customerService:CustomerService,
              private message:NzMessageService,
              private router:Router,
              private activatedRoute:ActivatedRoute,
              private fb:FormBuilder){
  }

  ngOnInit() {
    this.validateForm = this.fb.group({
      toDate:[null,Validators.required],
      fromDate:[null,Validators.required],
    })
    this.getCarById();
  }

  getCarById(){
    this.customerService.getCarById(this.id).subscribe((res)=>{
      console.log(res);
      this.processedImage = 'data:image/jpeg;base64,' + res.returnedImage;
      this.car = res;
    })
  }

  async bookCar(data:any){

    console.log(data);
    this.isSpinning = true;

    const bookCarDTO = {
      toDate:data.toDate,
      fromDate:data.fromDate,
      userId:StorageService.getUserId(),
      carId:this.id,
    }

    console.log(bookCarDTO)
    this.customerService.bookCar(bookCarDTO).subscribe((res)=>{
      this.isSpinning = false;
      this.message.success("Car Booking Request Submitted Successfully", { nzDuration: 5000 });
      this.router.navigateByUrl("/customer/dashboard");
    }, error => {
      this.isSpinning = false;
      this.message.error("Error While Booking Car", { nzDuration: 5000 });
      console.error(error);
    });
  }
}
