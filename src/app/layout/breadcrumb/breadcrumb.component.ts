import {Component, OnInit} from '@angular/core';
import { MenuItem } from 'primeng/api';
import { BreadcrumbModule } from 'primeng/breadcrumb';
import {NavigationEnd, Router} from "@angular/router";
import {filter} from "rxjs";

@Component({
  selector: 'app-breadcrumb',
  standalone: true,
  imports: [BreadcrumbModule],
  templateUrl: './breadcrumb.component.html',
  styleUrl: './breadcrumb.component.css'
})
export class BreadcrumbComponent  implements OnInit{
 public items: MenuItem[]= [ { label: 'Employee' },


 ];

  public home: MenuItem = { icon: 'pi pi-home', routerLink: '/' };
  constructor(private router:Router){

  }
  ngOnInit():void {
    this.router.events.pipe(filter(event =>event instanceof  NavigationEnd)).subscribe(
      ()=>this.updateBreadcrumb()


    )
  }
  private updateBreadcrumb(): void{
    const url:string =this.router.url;
    if (url === '/organisation'){
      this.items=[{label:'organisation'}];
    }
    else if (url ==='/') {
      this.items=[{label:'Employee'}]
    } else{
      //pourr les cas futures
    }
  }
}
