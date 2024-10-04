import {Component, OnInit} from '@angular/core';
import {MessageService, TreeNode} from "primeng/api";
import {OrganizationChartModule} from "primeng/organizationchart";
import {EmployeeService} from "../../../services/employee.service";
import {take} from "rxjs";
import {EmployeeResponse} from "../../../../api";

@Component({
  selector: 'app-organisation-chart',
  standalone: true,
  imports: [
    OrganizationChartModule
  ],
  templateUrl: './organisation-chart.component.html',
  styleUrl: './organisation-chart.component.scss'
})
export class OrganisationChartComponent  implements OnInit{
  selectedNodes!: TreeNode[];

  data: TreeNode[] = [];

  constructor(private  employeeService: EmployeeService, private  messageService: MessageService)  {
  }


  ngOnInit(): void {
    this.updateOrganizationChart();
  }
  private updateOrganizationChart():void{
    this.employeeService.getAllEmployees().pipe(take(1)).subscribe({
      next: (employeeResponse :EmployeeResponse[]):void => {
        this.data=this.transformEmployeeToTreeNodes(employeeResponse);


      },
      error: (): void => {
        this.messageService.add({severity: 'error', summary: 'Error occurred'})
      }
    })
  }

  private transformEmployeeToTreeNodes(employees:EmployeeResponse[]):TreeNode[] {
    let tree: TreeNode[] = [];
    let ceos:EmployeeResponse[] = employees.filter((e:EmployeeResponse )=>e.position==='CEO');
    ceos.forEach((ceo:EmployeeResponse ):void=> {
      let ceoNode:TreeNode={
        expanded:true,
        type:'person',
        data:{
          image:ceo.imageURL,
          name: ceo.firstName+""+ceo.lastName,
          title:ceo.position
        },
        children:this.getChildren(ceo.id,employees)
      };
      tree.push(ceoNode);
    })
    return tree;
  }

  private getChildren(superiorId:string, employees:EmployeeResponse[]):TreeNode[] {
    let children:TreeNode[] = [];
    let subordinates:EmployeeResponse[] = employees.filter((e:EmployeeResponse): boolean=>e.superiorId===superiorId);
     for (let subordinate of subordinates) {
       let node:TreeNode = {
         expanded:true,
         type:'person',
         data:{
           image:subordinate.imageURL,
           name:subordinate.firstName+""+subordinate.lastName,
           title:subordinate.position
         },
         children:this.getChildren(subordinate.id,employees)
       };
       children.push(node);
     }

    return children;

  }
}
