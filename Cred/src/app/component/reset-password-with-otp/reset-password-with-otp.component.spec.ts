import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResetPasswordWithOtpComponent } from './reset-password-with-otp.component';

describe('ResetPasswordWithOtpComponent', () => {
  let component: ResetPasswordWithOtpComponent;
  let fixture: ComponentFixture<ResetPasswordWithOtpComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ResetPasswordWithOtpComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ResetPasswordWithOtpComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
