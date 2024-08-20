import { Component, } from '@angular/core';
import { MenuItem } from 'primeng/api';
import { ButtonModule } from 'primeng/button';
import { MenubarModule } from 'primeng/menubar';


@Component({
  selector: 'app-header',
  standalone: true,
  imports: [MenubarModule,ButtonModule],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent  {
  public items: MenuItem[] =[

    {
      label: 'Employee',
      icon: 'pi pi-home'
    },
    {
      label:  'Company organisation chart',
      icon: 'pi pi-th-large'
    }
  ];

  imageUrl: string ="assets/logo/geek.png";

  imageWidth: number = 100;
  imageHeight: number = 50;

  
}


