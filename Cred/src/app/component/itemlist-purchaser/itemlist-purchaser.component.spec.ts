import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ItemlistPurchaserComponent } from './itemlist-purchaser.component';

describe('ItemlistPurchaserComponent', () => {
  let component: ItemlistPurchaserComponent;
  let fixture: ComponentFixture<ItemlistPurchaserComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ItemlistPurchaserComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ItemlistPurchaserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
