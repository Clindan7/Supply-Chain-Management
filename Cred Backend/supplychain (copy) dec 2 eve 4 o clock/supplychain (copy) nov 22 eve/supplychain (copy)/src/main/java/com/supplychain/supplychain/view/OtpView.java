package com.supplychain.supplychain.view;

import com.supplychain.supplychain.entity.Otp;

public class OtpView {
    private final int otpId;
     private final int otp;
    private final String email;
    
    public OtpView(Otp user) {
    this.otpId = user.getOtpId();
    this.otp = user.getOtp();
    this.email = user.getEmail();
   
  }
    
     public int getOtpId() {
   return otpId;
  }
    
   public int getOtp() {
    return otp;
    }
    
    public String getEmail() {
     return email;
     }
   
    }
    
    