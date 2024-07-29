import { Component } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AdminService} from "../../service/admin.service";
import {NzMessageService} from "ng-zorro-antd/message";
import {Router} from "@angular/router";

@Component({
  selector: 'app-post-car',
  templateUrl: './post-car.component.html',
  styleUrl: './post-car.component.css'
})
export class PostCarComponent {

  postCarForm!:FormGroup;

  isSpinning: boolean = false;
  selectedFile: File | null = null;
  imagePreview: string | ArrayBuffer | null = null;

  listOfBrands =["BMW","AUDI","TESLA","TOYOTA","HONDA","NISSAN"];
  listOfTypes  =["Petrol","Hybrid","Diesel","Electric"];
  listOfColor  =["Red","White","Blue","Black","Orange","Silver"];
  listOfTransmissions  =["Manual","Automatic"];

  constructor(private fb:FormBuilder,private adminService:AdminService,
              private message:NzMessageService,
              private router:Router) {

    this.postCarForm =  this.fb.group({

      name :[null,Validators.required],
      brand :[null,Validators.required],
      type :[null,Validators.required],
      color :[null,Validators.required],
      transmission :[null,Validators.required],
      price :[null,Validators.required],
      description :[null,Validators.required],
      year:[null,Validators.required]
    })

  }

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
    this.previewImage();
  }

   previewImage() {
     const reader = new FileReader();
     reader.onload = () => {
       this.imagePreview = reader.result;
     };
    if (this.selectedFile) {
      reader.readAsDataURL(this.selectedFile);
    }
  }

  async postCar(){

    const formData : FormData = new FormData();

    this.isSpinning =true;

    if (this.selectedFile) {
      formData.append('image', this.selectedFile);
    }
    formData.append('brand',this.postCarForm.get('brand')?.value);
    formData.append('name',this.postCarForm.get('name')?.value);
    formData.append('type',this.postCarForm.get('type')?.value);
    formData.append('color',this.postCarForm.get('color')?.value);
    formData.append('year',this.postCarForm.get('year')?.value);
    formData.append('transmission',this.postCarForm.get('transmission')?.value);
    formData.append('description',this.postCarForm.get('description')?.value);
    formData.append('price',this.postCarForm.get('price')?.value);

    this.adminService.postCar(formData).subscribe((res) => {
      this.isSpinning = false;
      this.message.success("Car Posted Successfully", { nzDuration: 5000 });
      this.router.navigateByUrl("/admin/dashboard");
      console.log(res);
    }, error => {
      this.isSpinning = false;
      this.message.error("Error While Posting Car", { nzDuration: 5000 });
      console.error(error);
    });

  }
}
