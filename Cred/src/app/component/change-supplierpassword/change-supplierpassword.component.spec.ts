import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChangeSupplierpasswordComponent } from './change-supplierpassword.component';

describe('ChangeSupplierpasswordComponent', () => {
  let component: ChangeSupplierpasswordComponent;
  let fixture: ComponentFixture<ChangeSupplierpasswordComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ChangeSupplierpasswordComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ChangeSupplierpasswordComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
