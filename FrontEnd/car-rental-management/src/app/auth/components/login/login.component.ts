import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit{

  isSpinning: boolean = false;

  loginForm!:FormGroup;

  constructor(private fb:FormBuilder) {
  }

  ngOnInit() {
    this.loginForm=this.fb.group({
      email: [null, [Validators.required, Validators.email]],
      password:[null,[Validators.required]],
    })
  }

  loginTo() {
    alert(this.loginForm.value.email);
  }
}
