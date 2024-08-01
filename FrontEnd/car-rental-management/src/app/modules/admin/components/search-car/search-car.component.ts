import { Component } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {AdminService} from "../../service/admin.service";

@Component({
  selector: 'app-search-car',
  templateUrl: './search-car.component.html',
  styleUrl: './search-car.component.css'
})
export class SearchCarComponent {

  searchCarForm!:FormGroup;
  isSpinning: boolean = false;

  listOfBrands =["BMW","AUDI","TESLA","TOYOTA","HONDA","NISSAN"];
  listOfTypes  =["Petrol","Hybrid","Diesel","Electric"];
  listOfColor  =["Red","White","Blue","Black","Orange","Silver"];
  listOfTransmissions  =["Manual","Automatic"];

  cars:any =[];

  constructor(private fb:FormBuilder,private adminService:AdminService) {

    this.searchCarForm = this.fb.group({
      brand:[null],
      type:[null],
      transmission:[null],
      color:[null]
    })
  }

  searchCar() {
    this.cars = [];

    this.isSpinning =  true;
    this.adminService.searchCar(this.searchCarForm.value).subscribe((res) =>{
      this.isSpinning = false;
      res.carDTOList.forEach((element: any) => {
        element.processedImg = 'data:image/jpeg;base64,' + element.returnedImage;
        this.cars.push(element);
      });

    })

  }
}
