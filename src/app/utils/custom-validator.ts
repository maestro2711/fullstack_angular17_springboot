import {AbstractControl, ValidationErrors} from "@angular/forms";

export function trimValidator(control: AbstractControl): ValidationErrors | null {
  if(control.value && control.value.trim().length === 0){
    return {trimValidator: true};
  }
  return null;
}

export function minAgeDateValidator(minAge: number) {
  return (control: AbstractControl): ValidationErrors | null => {
    if (!control.value) {
      return null;
    }

    const birthDate: Date = new Date(control.value);
    const currentDate: Date  = new Date();
    const diff: number = currentDate.getFullYear() - birthDate.getFullYear();
    const age: number = diff - (currentDate.getMonth() < birthDate.getMonth() || (currentDate.getMonth() === birthDate.getMonth() && currentDate.getDate() < birthDate.getDate()) ? 1 : 0);

    if (age < minAge) {
      return { minAgeDate: { requiredAge: minAge, actualAge: age } };
    }

    return null;
  };
}
