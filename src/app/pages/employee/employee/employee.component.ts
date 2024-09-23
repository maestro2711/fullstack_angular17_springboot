import {Component, OnInit, untracked} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, take} from "rxjs";
import { EmployeeService} from "../../../services/employee.service";
// @ts-ignore
import {EmployeeRequest, EmployeeResponse,SuperiorResponse} from "../../../../dto";
import {AsyncPipe, DatePipe, JsonPipe, LowerCasePipe, NgIf} from "@angular/common";
import {Table, TableModule} from "primeng/table";
import {Column} from "../../../models/column";
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
import {FormBuilder, FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {CalendarModule} from "primeng/calendar";
import {InputNumberModule} from "primeng/inputnumber";
import {InputMaskModule} from "primeng/inputmask";
import {minAgeDateValidator, trimValidator} from "../../../utils/custom-validator";
import {ConfirmationService, MessageService} from "primeng/api";
import {ErrorMessagesComponent} from "../../../shared/error-messages/error-messages.component";
import {EditDeleteComponent} from "../../../shared/edit-delete/edit-delete.component";

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
    DropdownModule,
    ReactiveFormsModule,
    CalendarModule,
    InputNumberModule,
    FormsModule,
    InputMaskModule, ErrorMessagesComponent, AsyncPipe, EditDeleteComponent, NgIf
  ],
  templateUrl: './employee.component.html',
  styleUrl: './employee.component.scss'
})
export class EmployeeComponent implements OnInit{

  public dialogTitle: string = "Add Employee";
  public employees: EmployeeResponse[] = [];
  public cols: Column[] = [];
  public globalFilterFields: string[] = [];
  public loading: boolean = true;
  public showDialog: boolean = false;
  public onViewMode:boolean=true;
  public employeeForm!: FormGroup;
  public superiorByPosition$!: Observable<SuperiorResponse[]> |null;

  public genderOptions = [
    { label: 'Men', value: 'MEN' },
    { label: 'Women', value: 'WOMEN' }
  ];
  public positionOptions = [
    { label: 'CEO', value: 'CEO' },
    { label: 'CTO', value: 'CTO' },
    { label: 'C00', value: 'COO' },
    { label: 'Team Manager Software', value: 'TEAM_MANAGER_SOFTWARE' },
    { label: 'Senior Software Developer', value: 'SENIOR_SOFTWARE_DEVELOPER' },
    { label: 'Software Developer', value: 'SOFTWARE_DEVELOPER' },
    { label: 'Junior Software Developer', value: 'JUNIOR_SOFTWARE_DEVELOPER' },
    { label: 'Working Student', value: 'WORKING_STUDENT' },
  ];


  constructor(
    private employeeService: EmployeeService,
    private messageService: MessageService,
    private confirmationService:ConfirmationService
  ){

  }

  ngOnInit(): void {
    this.initializeForm();
    this.getEmployees();
    this.setupCols();
  }

  public getSuperiorByPosition(): void{
    this.employeeForm.patchValue({
      superiorId:null
    })
    this.superiorByPosition$ = this.employeeService.getSuperiorsByPosition(this.employeeForm.value.position);
  }

  initializeForm(): void{
    this.employeeForm = new FormGroup({
      id: new FormControl(null),
      imageURL: new FormControl(null),
      gender: new FormControl(null, [Validators.required, trimValidator]),
      firstName: new FormControl('', [Validators.required, trimValidator]),
      lastName: new FormControl('', [Validators.required, trimValidator]),
      email: new FormControl('', [trimValidator, Validators.required, Validators.email] ),
      phone: new FormControl('', [Validators.required, trimValidator] ),
      dateOfBirth: new FormControl('', [Validators.required, minAgeDateValidator(18)]),
      city: new FormControl('', [Validators.required, trimValidator]),
      country: new FormControl('', [Validators.required, trimValidator]),
      remainingVacationDays: new FormControl(0, [Validators.required, Validators.min(0), Validators.max(30)]),
      onVacation: new FormControl(false),
      position: new FormControl(null, [Validators.required, trimValidator]),
      superiorId:new FormControl(null)
    });
  }

  public onSubmit(): void{
    if(!this.employeeForm.valid){
      this.employeeForm.markAllAsTouched();
      this.messageService.add({severity: 'error', summary: 'Error', detail: 'Please correct the form'})
    }else{
      if (this.employeeForm.value.id){
        //edit/update
        this.updateEmployee(this.employeeForm.value)
      }else{
        this.createEmployee(this.employeeForm.value);
      }

    }
  }

  public showEditDialog(employeeReponse: EmployeeResponse):void{
    this.employeeForm.enable();
    this.employeeForm.patchValue({
      ...employeeReponse
    });
    this.showDialog= true;
    this.onViewMode=false;
    this.dialogTitle = 'Edit Employee('+employeeReponse.firstName+')';
    this.superiorByPosition$ = this.employeeService.getSuperiorsByPosition(this.employeeForm.value.position);
  }
  public openCreateEmployee(): void{
    this.dialogTitle='Add Employee';
    this.showDialog = true;
    this.onViewMode=false;
    this.employeeForm.enable();
    this.employeeForm.reset();
    this.superiorByPosition$= null;
  }

  public editMode():void{
    this.dialogTitle='Edit Employee ('+this.employeeForm.value.firstName+')';
    this.employeeForm.enable();
    this.onViewMode=false;
  }

  public openViewEmployee(employee: EmployeeResponse):void{
    this.dialogTitle='View Employee ('+employee.firstName+')';
    this.showDialog = true;
    this.onViewMode=true;
    this.employeeForm.patchValue({
      ...employee
    })
    this.employeeForm.disable();

  }


  public delete(employee: EmployeeResponse):void{
    this.confirmationService.confirm({
      message: 'Do you want to delete this employee ' +employee.firstName,
      header: 'Delete Confirmation',
      icon: 'pi pi-info-circle',
      acceptButtonStyleClass:"p-button-danger p-button-text",
      rejectButtonStyleClass:"p-button-text p-button-text",
      acceptIcon:"none",
      rejectIcon:"none",

      accept: ():void => {
        this.employeeService.deleteEmployeeById(employee.id).pipe(take(1)).subscribe(
          {
            next:():void=>{
              this.employees = this.employees.filter((val :EmployeeResponse):boolean =>val.id !==employee.id);
              this.messageService.add({ severity: 'info', summary: 'Confirmed', detail: 'employee successfully deleted' });
            },
            error:():void=>{
              this.messageService.add({ severity: 'error', summary: 'Error', detail: 'employee  deleted Error' });
            }
          }
        )

      },

    });
  }

  private createEmployee(employeeRequest: EmployeeRequest): void{
    this.employeeService.createEmployee({
      ...employeeRequest,
       imageURL: 'https://afrogeek.fr/assets/img/hrconnect/men/1.png'}
    ).pipe(take(1))
      .subscribe({
        next: (employeeResponse: EmployeeResponse): void =>{
          this.employees.unshift(employeeResponse);
          this.showDialog = false;
          this.messageService.add({severity: 'success', summary: 'Success', detail: 'Create Employee successfully'});
        },
        error: (): void =>{
          this.messageService.add({severity: 'error', summary: 'Error', detail: 'Something went wrong'});
        }
      });
  }

  public updateEmployee(employeeRequest:EmployeeRequest):void{
    this.employeeService.updateEmployee(this.employeeForm.value.id,employeeRequest).pipe(take(1)).subscribe({
      next:(employeeResponse:EmployeeResponse):void=> {
        this.employees = this.employees.map((employeeResponseMap: EmployeeResponse): EmployeeResponse =>{
          if (employeeResponse.id === employeeResponseMap.id) {
            return employeeResponse;
          }
          return employeeResponseMap;
        });
        this.showDialog = false;
        this.messageService.add({severity: 'success', summary: 'Success', detail: 'update Employee successfully'});
    },
      error:():void=>{
        this.messageService.add({severity: 'error', summary: 'Error', detail: 'Something went wrong'});
      }
    })
  }

  public onGlobFilter(table: Table, event: Event): void {
    table.filterGlobal((event.target as HTMLInputElement).value, 'contains');


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

