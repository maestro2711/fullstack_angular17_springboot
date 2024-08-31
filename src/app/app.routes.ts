import { Routes } from '@angular/router';
import {OrganizationChart} from "primeng/organizationchart";
import {EmployeeComponent} from "./pages/employee/employee/employee.component";

export const routes: Routes = [
  {path:'',component:EmployeeComponent},
  {path: 'organisation',component:OrganizationChart},

];
