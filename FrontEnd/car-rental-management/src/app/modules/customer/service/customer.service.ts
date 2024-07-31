import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {StorageService} from "../../../auth/services/storage/storage.service";


const BASE_URL = ["http://localhost:8080"];

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  constructor(private http: HttpClient) { }


  getAllCars(): Observable<any> {

    return this.http.get(BASE_URL + "/api/customer/getAllCarsForCustomer", {
      headers: this.createAuthorizationHeader()
    });
  }

  getCarById(id:number):Observable<any>{
    return this.http.get(BASE_URL+"/api/customer/getCarById/"+id,{
      headers: this.createAuthorizationHeader()
    })
  }

  bookCar(bookCarDTO:any):Observable<any>{
    return this.http.post(BASE_URL+"/api/customer/bookCar",bookCarDTO,{
      headers: this.createAuthorizationHeader()
    })
  }

  getBookingCarByUserId():Observable<any>{
    return this.http.get(BASE_URL+"/api/customer/getBookingCarByUserId/" + StorageService.getUserId(),{
      headers: this.createAuthorizationHeader()
    })
  }

  createAuthorizationHeader(): HttpHeaders {

    let authHeader: HttpHeaders = new HttpHeaders();
    return authHeader.set(
      'Authorization',
      'Bearer ' + StorageService.getToken()
    );
  }
}
