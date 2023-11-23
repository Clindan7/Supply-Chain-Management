import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PurchaserProfileComponent } from './purchaser-profile.component';

describe('PurchaserProfileComponent', () => {
  let component: PurchaserProfileComponent;
  let fixture: ComponentFixture<PurchaserProfileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PurchaserProfileComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PurchaserProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
