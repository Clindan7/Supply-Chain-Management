import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Viewpurchaser2Component } from './viewpurchaser2.component';

describe('Viewpurchaser2Component', () => {
  let component: Viewpurchaser2Component;
  let fixture: ComponentFixture<Viewpurchaser2Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Viewpurchaser2Component ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Viewpurchaser2Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
