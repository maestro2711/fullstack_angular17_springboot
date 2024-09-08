import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'age',
  standalone: true
})
export class AgePipe implements PipeTransform {

  transform(birthDate: string | Date):number  {
    const today=new Date();
    const birth = new Date(birthDate);
    let age = today.getFullYear()- birth.getFullYear();
    let month=today.getMonth()- birth.getMonth();
    if (month<0 ||(month = 0 && today.getDate()< birth.getDate())){
      age--;
    }

    return age;
  }

}
