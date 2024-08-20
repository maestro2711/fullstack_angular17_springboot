import { Component } from '@angular/core';
import {DividerModule} from "primeng/divider";

@Component({
  selector: 'app-foooter',
  standalone: true,
  imports: [
    DividerModule
  ],
  templateUrl: './foooter.component.html',
  styleUrl: './foooter.component.css'
})
export class FoooterComponent {

}
