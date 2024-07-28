import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css'
})
export class SignupComponent implements OnInit{

  isSpinning:boolean=false;

  signupForm! :FormGroup;


  constructor(private fb:FormBuilder) {
  }

  ngOnInit(){

    this.signupForm = this.fb.group({
      name:[null,[Validators.required]],
      email: [null, [Validators.required, Validators.email]],
      password:[null,[Validators.required]],
      checkPassword:[null,[Validators.required,this.confirmationValidate]],
    })
  }

  confirmationValidate = (control :FormControl): { [s: string]: boolean} => {

    if(!control.value){
      return {required:true};
    }else if(control.value !== this.signupForm.controls['password'].value){
      return {confirm:true,error:true};
    }
    return {}
  }

  register(){
    console.log(this.signupForm.value)
  }
}
