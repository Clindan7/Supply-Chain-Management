package com.supplychain.supplychain.repository;

import java.util.Collection;

import org.springframework.data.repository.Repository;

import com.supplychain.supplychain.entity.Otp;

public interface OtpRepository extends Repository<Otp, Integer>{
    
    Otp save( Otp otp);

    void deleteAll();

    Collection<Otp>findAll();
}