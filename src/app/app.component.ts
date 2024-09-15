import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { ButtonModule } from 'primeng/button';
import { MenubarModule } from 'primeng/menubar';
import { HeaderComponent } from './layout/header/header.component';
import {BreadcrumbComponent} from "./layout/breadcrumb/breadcrumb.component";
import {FoooterComponent} from "./layout/foooter/foooter.component";
import { DividerModule } from 'primeng/divider';
import {EmployeeComponent} from "./pages/employee/employee/employee.component";
import {OrganisationChartComponent} from "./pages/organisation-chart/organisation-chart/organisation-chart.component";
import {EmployeeService} from "./services/employee.service";
import {MessageService} from "primeng/api";


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, ButtonModule, HeaderComponent,
    MenubarModule, BreadcrumbComponent, FoooterComponent,DividerModule,EmployeeComponent,OrganisationChartComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
  providers: [EmployeeService,MessageService]
})
export class AppComponent {
  title = 'frontend';

}
