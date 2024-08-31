import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {EmployeeResponse} from "../../api";

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private apiUrl:string = 'http://localhost:8081/employees';
  constructor(private http:HttpClient) {}
  public getAllEmployees():Observable<EmployeeResponse[]>{
    return this.http.get<EmployeeResponse[]>(this.apiUrl);
  }
}
