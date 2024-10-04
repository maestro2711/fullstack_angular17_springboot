import { Routes } from '@angular/router';
import {OrganizationChart} from "primeng/organizationchart";
import {EmployeeComponent} from "./pages/employee/employee/employee.component";
import {OrganisationChartComponent} from "./pages/organisation-chart/organisation-chart/organisation-chart.component";

export const routes: Routes = [
  {path:'',component:EmployeeComponent},
  {path: 'organisation',component:OrganisationChartComponent},
  {path: 'employee',component:EmployeeComponent},

];
