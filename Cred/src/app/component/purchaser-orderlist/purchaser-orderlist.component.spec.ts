import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PurchaserOrderlistComponent } from './purchaser-orderlist.component';

describe('PurchaserOrderlistComponent', () => {
  let component: PurchaserOrderlistComponent;
  let fixture: ComponentFixture<PurchaserOrderlistComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PurchaserOrderlistComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PurchaserOrderlistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
