import {Component, Input} from '@angular/core';
import {AbstractControl, ControlContainer, FormGroup} from "@angular/forms";
import {ValidatorService} from "../../services/validator.service";
import {NgIf} from "@angular/common";

@Component({
  selector: '[geek-error-messages]',
  standalone: true,
  imports: [
    NgIf
  ],
  templateUrl: './error-messages.component.html',
  styleUrl: './error-messages.component.scss'
})
export class ErrorMessagesComponent {
  @Input({required:true})
  public control!:string;
  constructor(
    private controlContainer:ControlContainer,
    public validatorService:ValidatorService,
  ) {
  }
  get form():FormGroup{
    return this.controlContainer.control as FormGroup;
  }
  get formControl():AbstractControl{
    return this.form.get(this.control) as AbstractControl;
  }

  get isNotValid():boolean{
    return this.formControl.invalid && (this.formControl.touched || this.formControl.dirty);
  }

}
