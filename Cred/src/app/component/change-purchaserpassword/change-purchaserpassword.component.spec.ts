import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChangePurchaserpasswordComponent } from './change-purchaserpassword.component';

describe('ChangePurchaserpasswordComponent', () => {
  let component: ChangePurchaserpasswordComponent;
  let fixture: ComponentFixture<ChangePurchaserpasswordComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ChangePurchaserpasswordComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ChangePurchaserpasswordComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
