import {Component, OnInit} from '@angular/core';
import {StorageService} from "./auth/services/storage/storage.service";
import {Router} from "@angular/router";
import Swal from 'sweetalert2';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit{

  title = 'car-rental-management';

  isCustomerLoggedIn:boolean = StorageService.isCustomerLoggedIn();

  isAdminLoggedIn:boolean = StorageService.isAdminLoggedIn();

  constructor(private router : Router) {

  }

  ngOnInit() {

    this.router.events.subscribe(event =>{
      if(event.constructor.name === "NavigationEnd"){
        this.isAdminLoggedIn = StorageService.isAdminLoggedIn();
        this.isCustomerLoggedIn = StorageService.isCustomerLoggedIn();
      }
    })
  }

  async logOut() {
    const result = await Swal.fire({
      title: 'Are you sure?',
      text: 'You will be logged out.',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3c8c3c',
      cancelButtonColor: '#f85555',
      confirmButtonText: 'Yes, log out!'
    });

    if (result.isConfirmed) {
      StorageService.logOut();
      await this.router.navigateByUrl("/login");
    }
  }


}
