import { Injectable } from '@angular/core';
import {AbstractControl, ValidationErrors} from "@angular/forms";

@Injectable({
  providedIn: 'root'
})
export class ValidatorService {

  constructor() { }

  public getErrorMessage(formControl:AbstractControl|null):string{
    if (!formControl){
      return '';
    }

    const errors:ValidationErrors|null=formControl.errors;

    if (!errors){
      return 'successfully login';
    }

    if (errors['email']){
      return 'please enter a valid email address';
    }
    if (errors['required']){
      return 'This field is required';
    }
    return 'invalid input';

  }
}
