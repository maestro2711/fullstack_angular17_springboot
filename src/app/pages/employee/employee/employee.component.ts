import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import { EmployeeService} from "../../../services/employee.service";
import {EmployeeResponse} from "../../../../api";
import {JsonPipe} from "@angular/common";
import {TableModule} from "primeng/table";

@Component({
  selector: 'app-employee',
  standalone: true,
  imports: [
    JsonPipe,
    TableModule
  ],
  templateUrl: './employee.component.html',
  styleUrl: './employee.component.scss'
})
export class EmployeeComponent  implements OnInit{
  public employees :EmployeeResponse[]=[];

  constructor(private employeeService:EmployeeService) {
  }

  ngOnInit():void {
    this.getEmployees()
  }

  private getEmployees():void{
    this.employeeService.getAllEmployees().subscribe({
      next:(res: EmployeeResponse[]):void => {
        this.employees= res;

      },
      error:(err:Error):void =>{
        alert(err);
        //console.log(err);
      }
    })
  }
  }

