import {Component, OnInit} from '@angular/core';
import {AdminService} from "../../service/admin.service";
import {ActivatedRoute} from "@angular/router";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-update-car',
  templateUrl: './update-car.component.html',
  styleUrl: './update-car.component.css'
})
export class UpdateCarComponent implements OnInit{

  id:number =  this.activatedRoute.snapshot.params["id"];

  existingImage: string | null = null;
  imagePreview: string | null = null;
  isSpinning: boolean = false;
  updateForm!: FormGroup;

  listOfBrands =["BMW","AUDI","TESLA","TOYOTA","HONDA","NISSAN"];
  listOfTypes  =["Petrol","Hybrid","Diesel","Electric"];
  listOfColor  =["Red","White","Blue","Black","Orange","Silver"];
  listOfTransmissions  =["Manual","Automatic"];


  constructor(private adminService:AdminService,
              private activatedRoute:ActivatedRoute,
              private fb: FormBuilder) {
  }

  ngOnInit() {

    this.updateForm = this.fb.group({
      name :[null,Validators.required],
      brand :[null,Validators.required],
      type :[null,Validators.required],
      color :[null,Validators.required],
      transmission :[null,Validators.required],
      price :[null,Validators.required],
      description :[null,Validators.required],
      year:[null,Validators.required]
    })

    this.getCarById();
  }

  getCarById(){
    this.isSpinning=true;
    this.adminService.getCarById(this.id).subscribe((res)=>{
      //console.log(res);
      this.isSpinning=false;
      const catDTO = res;
      this.existingImage = 'data:image/jpeg;base64,' + res.returnedImage;
      console.log(catDTO);
      console.log(this.existingImage);
      this.updateForm.patchValue(catDTO);
    })
  }

  onFileSelected(event:any) {

  }
}
