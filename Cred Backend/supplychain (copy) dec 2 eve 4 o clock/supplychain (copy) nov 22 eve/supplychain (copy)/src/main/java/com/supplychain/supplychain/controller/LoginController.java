/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supplychain.supplychain.controller;

import com.supplychain.supplychain.form.LoginForm;
import com.supplychain.supplychain.service.UserService;
import com.supplychain.supplychain.view.LoginView;
import com.supplychain.supplychain.view.SupplierView;
import com.supplychain.supplychain.view.UserView;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;
    

    @GetMapping 
    public UserView currentUser() {
        return userService.currentUser();
    }





    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping
    public LoginView login(@Valid @RequestBody LoginForm form, Errors errors) {
        return userService.login(form, errors);
    }

    @PutMapping
    public LoginView refresh(@RequestBody String refreshToken) {
        return userService.refresh(refreshToken);
    }
}
