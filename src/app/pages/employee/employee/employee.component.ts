import {Component, OnInit, untracked} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, take} from "rxjs";
import { EmployeeService} from "../../../services/employee.service";
import {EmployeeResponse} from "../../../../api";
import {DatePipe, JsonPipe, LowerCasePipe} from "@angular/common";
import {Table, TableModule} from "primeng/table";
import {Column} from "../../../models/column";
import {
  checkCustomElementSelectorForErrors
} from "@angular/compiler-cli/src/ngtsc/annotations/component/src/diagnostics";
import {AvatarModule} from "primeng/avatar";
import {TagModule} from "primeng/tag";
import {AgePipe} from "../../../pipes/age.pipe";
import {IconFieldModule} from "primeng/iconfield";
import {InputIconModule} from "primeng/inputicon";
import {Button, ButtonDirective} from "primeng/button";
import {Ripple} from "primeng/ripple";
import {InputTextModule} from "primeng/inputtext";
import {DialogModule} from "primeng/dialog";
import {DividerModule} from "primeng/divider";
import {InputSwitchModule} from "primeng/inputswitch";
import {DropdownModule} from "primeng/dropdown";

@Component({
  selector: 'app-employee',
  standalone: true,
  imports: [
    JsonPipe,
    TableModule,
    AvatarModule,
    TagModule,
    DatePipe,
    AgePipe,
    ButtonDirective,
    Ripple,
    LowerCasePipe,
    IconFieldModule,
    InputIconModule,
    InputTextModule,
    Button,
    DialogModule,
    DividerModule,
    InputSwitchModule,
    DropdownModule
  ],
  templateUrl: './employee.component.html',
  styleUrl: './employee.component.scss'
})
export class EmployeeComponent implements OnInit{

  public employees: EmployeeResponse[] = [];
  public cols: Column[] = [];
  public globalFilterFields: string[] = [];
  public loading: boolean = true;
  public showDialog: boolean =false;
  public dialogTitle: string | undefined;


  constructor(private employeeService: EmployeeService){

  }

  ngOnInit(): void {
    this.getEmployees();
    this.setupCols();
  }

  public onGlobFilter(table: Table, event: Event): void {
    table.filterGlobal((event.target as HTMLInputElement).value, 'contains');
  }
  public openCreateEmployee():void{
    this.showDialog = true;

  }

  private setupCols(): void{
    this.cols = [
      { field: 'imageURL', header: 'Image' },
      { field: 'gender', header: 'Gender' },
      { field: 'firstName', header: 'First Name' },
      { field: 'lastName', header: 'Last Name' },
      { field: 'email', header: 'Email' },
      { field: 'phone', header: 'Phone' },
      { field: 'dateOfBirth', header: 'Age' },
      { field: 'city', header: 'City' },
      { field: 'country', header: 'Country' },
      { field: 'remainingVacationDays', header: 'Remaining Vacation Days' },
      { field: 'onVacation', header: 'On Vacation' },
      { field: 'position', header: 'Position' },
      { field: 'superiorName', header: 'Superior Name' }
    ];
    this.globalFilterFields = this.cols.map((col: Column)=>col.field);
  }


  private getEmployees(): void{
    this.employeeService.getAllEmployees()
      .pipe(take(1))
      .subscribe({
        next: (res: EmployeeResponse[]): void=>{
          this.loading = false;
          this.employees = res;
        },
        error: (err: Error): void => {
          //alert(err);
          console.log("error",err);
        }
      });
  }

}

