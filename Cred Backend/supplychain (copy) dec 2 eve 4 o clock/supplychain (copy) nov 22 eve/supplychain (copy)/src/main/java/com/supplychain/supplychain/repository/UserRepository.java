/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supplychain.supplychain.repository;

import com.supplychain.supplychain.entity.User;
import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

/**
 *
 * @author nirmal
 */
public interface UserRepository extends Repository<User, Integer> {

    Optional<User> findById(Integer userId);

    User findByUserId(Integer userId);


    Optional<User> findByUserIdAndPassword(Integer userId, String password);

    Optional<User> findByEmail(String email);

    User save(User user);
    
    Collection<User> findAll();

@Query(value="select * from user where role=?",nativeQuery = true)
    Collection<User> findUsers(Integer role);

    void delete(User user);

    

    @Query(value="select role from user where email=?",nativeQuery = true)
    Byte findRoleByEmail(String email);

    Collection<User> findUserIdByEmail( String email);
}
