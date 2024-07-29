import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../services/auth/auth.service";
import {NzMessageService} from "ng-zorro-antd/message";
import {Router} from "@angular/router";
import {StorageService} from "../../services/storage/storage.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit{

  isSpinning: boolean = false;

  loginForm!:FormGroup;

  constructor(private fb:FormBuilder,private authService:AuthService,private message:NzMessageService,
              private router:Router) {
  }

  ngOnInit() {
    this.loginForm=this.fb.group({
      email: [null, [Validators.required, Validators.email]],
      password:[null,[Validators.required]],
    })
  }

  async loginTo() {

    this.isSpinning = true;

    this.authService.login(this.loginForm.value).subscribe(
      (res) => {
        this.isSpinning = false;
        if (res.userId != null) {
          this.message.success("Login Successfully", { nzDuration: 5000 });
          console.log(res)
          const user = {
            id:res.userId,
            role:res.userRole
          }
          StorageService.saveUser(user);
          StorageService.saveToken(res.jwt);

          if(StorageService.isAdminLoggedIn()){
            this.router.navigateByUrl("/admin/dashboard");
          }else if(StorageService.isCustomerLoggedIn()){
            this.router.navigateByUrl("/customer/dashboard");
          }else {
            this.message.error("Bad Credentials....", { nzDuration: 50000 });
          }
        } else {
          this.message.error("Login Not Successfully", { nzDuration: 5000 });
        }
      },
      (error) => {
        this.isSpinning = false;
        console.error(error);
        this.message.error(error.error || "An error occurred during Login", { nzDuration: 5000 });
      }
    );
  }
}
