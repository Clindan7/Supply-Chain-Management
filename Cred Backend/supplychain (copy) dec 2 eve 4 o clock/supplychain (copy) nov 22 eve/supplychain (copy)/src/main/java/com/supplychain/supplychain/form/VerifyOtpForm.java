package com.supplychain.supplychain.form;

import javax.validation.constraints.NotBlank;

public class VerifyOtpForm {

    @NotBlank
    private Integer otp;
    private String newPassword;
    private String cnewPassword;
  

    public Integer getOtp() {
        return otp;
    }

    public void setOtp(Integer otp) {
        this.otp = otp;
    }
    
    public String getNewPassword() {
        return newPassword;
    }
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
    public String getCnewPassword() {
        return cnewPassword;
    }
    public void setCnewPassword(String cnewPassword) {
        this.cnewPassword = cnewPassword;


    }
}

