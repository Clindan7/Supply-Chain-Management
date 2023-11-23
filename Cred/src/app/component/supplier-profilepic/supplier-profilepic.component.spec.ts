import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SupplierProfilepicComponent } from './supplier-profilepic.component';

describe('SupplierProfilepicComponent', () => {
  let component: SupplierProfilepicComponent;
  let fixture: ComponentFixture<SupplierProfilepicComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SupplierProfilepicComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SupplierProfilepicComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
