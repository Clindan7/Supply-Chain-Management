package com.supplychain.supplychain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.supplychain.supplychain.form.VerifyOtpForm;

@Entity
public class Otp {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer otpId;
    private Integer otp;
    private String email;

    public Otp() {
    }
    public Otp(VerifyOtpForm form) {

        this.otp=form.getOtp();

    }

    public Otp update(VerifyOtpForm form){
        this.otp=form.getOtp();
        return this;
    }
    public Integer getOtpId() {
        return otpId;
    }
    public void setOtpId(Integer otpId) {
        this.otpId = otpId;
    }
    public Integer getOtp() {
        return otp;
    }
    public void setOtp(Integer otp) {
        this.otp = otp;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    
}
