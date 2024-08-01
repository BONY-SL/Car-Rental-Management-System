import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {StorageService} from "../../../auth/services/storage/storage.service";


const BASE_URL = ["http://localhost:8080"];

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http: HttpClient) {
  }

  postCar(carDTO: any): Observable<any> {

    return this.http.post(BASE_URL + "/api/admin/postCar", carDTO, {
      headers: this.createAuthorizationHeader()
    });
  }

  updateCar(id:number,carDTO: any): Observable<any> {

    return this.http.put(BASE_URL + "/api/admin/updateCarById/"+id, carDTO, {
      headers: this.createAuthorizationHeader()
    });
  }


  getAllCars(): Observable<any> {

    return this.http.get(BASE_URL + "/api/admin/getAllCars", {
      headers: this.createAuthorizationHeader()
    });
  }

  deleteCar(id:number):Observable<any>{

    return this.http.delete(BASE_URL+"/api/admin/deleteCar/"+id,{
      headers: this.createAuthorizationHeader()
    })
  }

  getCarById(id:number):Observable<any>{
    return this.http.get(BASE_URL+"/api/admin/getCarById/"+id,{
      headers: this.createAuthorizationHeader()
    })
  }

  getBookingCarsByCustomer(): Observable<any> {
    return this.http.get(BASE_URL + "/api/admin/getBookingCarsByCustomer", {
      headers: this.createAuthorizationHeader()
    });
  }

  changeBookingStatus(id: number, status: string): Observable<any> {
    return this.http.put(BASE_URL + `/api/admin/changeBookingStatus/${id}/${status}`, {}, {
      headers: this.createAuthorizationHeader()
    });
  }

  searchCar(searchCarDTO: any): Observable<any> {

    return this.http.post(BASE_URL + "/api/admin/searchCar", searchCarDTO, {
      headers: this.createAuthorizationHeader()
    });
  }


  createAuthorizationHeader(): HttpHeaders {

    let authHeader: HttpHeaders = new HttpHeaders();
    return authHeader.set(
      'Authorization',
      'Bearer ' + StorageService.getToken()
    );
  }
}
