import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Supplierlist2Component } from './supplierlist2.component';

describe('Supplierlist2Component', () => {
  let component: Supplierlist2Component;
  let fixture: ComponentFixture<Supplierlist2Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Supplierlist2Component ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Supplierlist2Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
