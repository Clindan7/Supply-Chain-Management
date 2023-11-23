import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FrontpageSupplierComponent } from './frontpage-supplier.component';

describe('FrontpageSupplierComponent', () => {
  let component: FrontpageSupplierComponent;
  let fixture: ComponentFixture<FrontpageSupplierComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FrontpageSupplierComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FrontpageSupplierComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
