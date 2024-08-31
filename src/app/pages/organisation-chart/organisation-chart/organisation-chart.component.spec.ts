import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OrganisationChartComponent } from './organisation-chart.component';

describe('OrganisationChartComponent', () => {
  let component: OrganisationChartComponent;
  let fixture: ComponentFixture<OrganisationChartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OrganisationChartComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(OrganisationChartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
