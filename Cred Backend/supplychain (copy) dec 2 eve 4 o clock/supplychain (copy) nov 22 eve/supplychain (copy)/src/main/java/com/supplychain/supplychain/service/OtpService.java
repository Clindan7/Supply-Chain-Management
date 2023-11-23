package com.supplychain.supplychain.service;

import java.util.Collection;

import com.supplychain.supplychain.entity.Otp;
import com.supplychain.supplychain.form.VerifyOtpForm;

public interface OtpService {
    

    // Otp add(VerifyOtpForm form);
    Collection<Otp> add(VerifyOtpForm form);
}