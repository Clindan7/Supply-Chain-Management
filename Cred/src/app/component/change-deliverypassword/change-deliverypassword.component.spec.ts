import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChangeDeliverypasswordComponent } from './change-deliverypassword.component';

describe('ChangeDeliverypasswordComponent', () => {
  let component: ChangeDeliverypasswordComponent;
  let fixture: ComponentFixture<ChangeDeliverypasswordComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ChangeDeliverypasswordComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ChangeDeliverypasswordComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
