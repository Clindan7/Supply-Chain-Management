package com.supplychain.supplychain.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.supplychain.supplychain.entity.Otp;
import com.supplychain.supplychain.form.VerifyOtpForm;
import com.supplychain.supplychain.service.OtpService;


@RestController
@RequestMapping("/verifyotp")
public class OtpController {
    
    @Autowired 
    OtpService otpService;

    @PostMapping
    public Collection<Otp> add(@RequestBody VerifyOtpForm form){
        return otpService.add(form);
    }
}
