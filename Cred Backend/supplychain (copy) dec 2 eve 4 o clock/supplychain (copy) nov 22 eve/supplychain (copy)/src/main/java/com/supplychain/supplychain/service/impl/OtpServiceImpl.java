package com.supplychain.supplychain.service.impl;


import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.supplychain.supplychain.entity.Otp;
import com.supplychain.supplychain.entity.User;
import com.supplychain.supplychain.form.VerifyOtpForm;
import com.supplychain.supplychain.repository.OtpRepository;
import com.supplychain.supplychain.repository.UserRepository;
import com.supplychain.supplychain.service.OtpService;

@Service
public class OtpServiceImpl implements OtpService {

    @Autowired
    private OtpRepository otpRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    // for adding category
    @Override
    public Collection<Otp> add(VerifyOtpForm form) {

        Collection<Otp> otp3 = otpRepository.findAll();
        for (Otp otp2 : otp3) {

            Collection<User> userID = userRepository.findUserIdByEmail(otp2.getEmail());

            for (User userId : userID) {
                System.out.println(userId.getUserId());
                User userId2 = userRepository.findById(userId.getUserId()).get();

                if ((form.getOtp().equals(otp2.getOtp()))) {

                    if (form.getNewPassword().equals(form.getCnewPassword()))

                    {
                        System.out.println(form.getNewPassword() + form.getCnewPassword() + "=======aaaaaaaaaa");
                        userId2.setPassword(passwordEncoder.encode(form.getCnewPassword()));
                        userRepository.save(userId2);

                    } else {
                        return null;
                    }

                    return null;

                }

            }

        }
        
        return otp3;
    }
}
 