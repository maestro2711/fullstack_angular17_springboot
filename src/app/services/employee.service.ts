import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {EmployeeRequest, EmployeeResponse} from "../../api";
// @ts-ignore
import {Position} from "../model/position";

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private apiUrl:string = 'http://localhost:8081/employees';

  constructor(private http:HttpClient) {}

  public getAllEmployees():Observable<EmployeeResponse[]>{
    return this.http.get<EmployeeResponse[]>(this.apiUrl);
  }

  // @ts-ignore
  public getSuperiorsByPosition(position: Position): Observable<SuperiorResponse[]> {
    const params: HttpParams = new HttpParams().set('position', position);
    // @ts-ignore
    return this.http.get<SuperiorResponse[]>(this.apiUrl + '/superiors', {params});
  }

  public createEmployee(employeeRequest: EmployeeRequest): Observable<EmployeeResponse> {
    return this.http.post<EmployeeResponse>(`${this.apiUrl}`, employeeRequest);
  }

  public updateEmployee(id:string, employeeRequest:EmployeeRequest):Observable<EmployeeResponse>{
    return this.http.put<EmployeeResponse>('${this.apiurl}/${id}',employeeRequest);
  }




}
