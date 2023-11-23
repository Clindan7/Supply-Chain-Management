/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supplychain.supplychain.service;

import com.supplychain.supplychain.entity.User;
import com.supplychain.supplychain.exception.BadRequestException;
import com.supplychain.supplychain.exception.NotFoundException;
import com.supplychain.supplychain.form.ChangePasswordForm;
import com.supplychain.supplychain.form.LoginForm;
import com.supplychain.supplychain.form.UserForm;
import com.supplychain.supplychain.view.LoginView;
import com.supplychain.supplychain.view.SupplierView;
import com.supplychain.supplychain.view.UserView;
import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

public interface UserService {

    UserView add(UserForm form);

    User getCurrentUser();

    UserView currentUser();

    // UserView currentPurchaser();


    LoginView login(LoginForm form, Errors errors) throws BadRequestException;

    LoginView refresh(String refreshToken) throws BadRequestException;

    Collection<User> list();

    Collection<User>listSupplier();

    Collection<User>listPurchaser();
    

    UserView update(Integer userId, UserForm form) throws NotFoundException;


    UserView updatepassword(ChangePasswordForm form);
    
    UserView updateLoggedUser(UserForm form);

    void delete(Integer userId) throws NotFoundException;

    UserView getUser(Integer userId);
}
