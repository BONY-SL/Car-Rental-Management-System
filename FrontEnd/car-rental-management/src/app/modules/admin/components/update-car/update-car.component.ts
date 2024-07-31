import {Component, OnInit} from '@angular/core';
import {AdminService} from "../../service/admin.service";
import {ActivatedRoute, Router} from "@angular/router";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {NzMessageService} from "ng-zorro-antd/message";
import Swal from "sweetalert2";

@Component({
  selector: 'app-update-car',
  templateUrl: './update-car.component.html',
  styleUrl: './update-car.component.css'
})
export class UpdateCarComponent implements OnInit{

  id:number =  this.activatedRoute.snapshot.params["id"];
  existingImage: string | null = null;
  isSpinning: boolean = false;
  updateForm!: FormGroup;



  imageChanged:boolean=false;
  selectedFile:any;
  imagePreview: string | ArrayBuffer | null = null;
  listOfOption: Array<{label: string;value:string}> = [];



  listOfBrands =["BMW","AUDI","TESLA","TOYOTA","HONDA","NISSAN"];
  listOfTypes  =["Petrol","Hybrid","Diesel","Electric"];
  listOfColor  =["Red","White","Blue","Black","Orange","Silver"];
  listOfTransmissions  =["Manual","Automatic"];


  constructor(private adminService:AdminService,
              private activatedRoute:ActivatedRoute,
              private fb: FormBuilder,
              private router:Router,
              private message:NzMessageService) {
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

    this.selectedFile = event.target.files[0];
    this.imageChanged = true;
    this.existingImage = null;
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

  async updateCar(){
    const formData : FormData = new FormData();

    this.isSpinning =true;

    if (this.imageChanged && this.selectedFile) {
      formData.append('image', this.selectedFile);
    }
    formData.append('brand',this.updateForm.get('brand')?.value);
    formData.append('name',this.updateForm.get('name')?.value);
    formData.append('type',this.updateForm.get('type')?.value);
    formData.append('color',this.updateForm.get('color')?.value);
    formData.append('year',this.updateForm.get('year')?.value);
    formData.append('transmission',this.updateForm.get('transmission')?.value);
    formData.append('description',this.updateForm.get('description')?.value);
    formData.append('price',this.updateForm.get('price')?.value);


    Swal.fire({
      title: 'Are you sure?',
      text: 'Do you want to update the car details?',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3c8c3c',
      cancelButtonColor: '#f85555',
      confirmButtonText: 'Yes, update it!',
      cancelButtonText: 'No, keep it'
    }).then((result) => {
      if (result.isConfirmed) {
        this.adminService.updateCar(this.id, formData).subscribe((res) => {
          this.isSpinning = false;
          this.message.success("Car Details Updated Successfully", { nzDuration: 5000 });
          this.router.navigateByUrl("/admin/dashboard");
          console.log(res);
        }, error => {
          this.isSpinning = false;
          this.message.error("Error While Updating Car", { nzDuration: 5000 });
          console.error(error);
        });
      } else {
        this.isSpinning = false;
      }
    });
  }

}
