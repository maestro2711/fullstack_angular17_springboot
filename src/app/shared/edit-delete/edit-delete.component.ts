import {Component, EventEmitter, Input, input, InputSignal, Output} from '@angular/core';
import {ButtonDirective} from "primeng/button";
import {Ripple} from "primeng/ripple";
import {EmployeeResponse} from "../../../api";
import {NgIf} from "@angular/common";

@Component({
  selector: 'app-edit-delete',
  standalone: true,
  imports: [
    ButtonDirective,
    Ripple,
    NgIf
  ],
  templateUrl: './edit-delete.component.html',
  styleUrl: './edit-delete.component.scss'
})
export class EditDeleteComponent {
   //employee:InputSignal<EmployeeResponse>= input.required()

  @Input({required:true})
  employeeProp!:EmployeeResponse



  @Output()
  public onDelete:EventEmitter<EmployeeResponse>=new EventEmitter();

  @Output()
  public onEdit:EventEmitter<EmployeeResponse> = new EventEmitter();

  @Output()
  public onView:EventEmitter<EmployeeResponse> = new EventEmitter();

  public edit(employee:EmployeeResponse):void{
    this.onEdit.emit(employee);
  }

  public delete(employee:EmployeeResponse):void{
    this.onDelete.emit(employee);
  }

  public view(employee:EmployeeResponse):void{
    this.onView.emit(employee);
  }
}
