import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { ButtonModule } from 'primeng/button';
import { MenubarModule } from 'primeng/menubar';
import { HeaderComponent } from './layout/header/header.component';
import {BreadcrumbComponent} from "./layout/breadcrumb/breadcrumb.component";
import {FoooterComponent} from "./layout/foooter/foooter.component";
import { DividerModule } from 'primeng/divider';


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, ButtonModule, HeaderComponent,
    MenubarModule, BreadcrumbComponent, FoooterComponent,DividerModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'frontend';

}
