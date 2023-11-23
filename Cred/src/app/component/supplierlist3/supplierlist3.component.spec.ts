import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Supplierlist3Component } from './supplierlist3.component';

describe('Supplierlist3Component', () => {
  let component: Supplierlist3Component;
  let fixture: ComponentFixture<Supplierlist3Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Supplierlist3Component ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Supplierlist3Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
