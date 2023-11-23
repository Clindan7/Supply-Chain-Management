import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChatSupplierComponent } from './chat-supplier.component';

describe('ChatSupplierComponent', () => {
  let component: ChatSupplierComponent;
  let fixture: ComponentFixture<ChatSupplierComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ChatSupplierComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ChatSupplierComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
