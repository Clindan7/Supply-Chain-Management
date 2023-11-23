import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ItemMainComponent } from './item-main.component';

describe('ItemMainComponent', () => {
  let component: ItemMainComponent;
  let fixture: ComponentFixture<ItemMainComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ItemMainComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ItemMainComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
