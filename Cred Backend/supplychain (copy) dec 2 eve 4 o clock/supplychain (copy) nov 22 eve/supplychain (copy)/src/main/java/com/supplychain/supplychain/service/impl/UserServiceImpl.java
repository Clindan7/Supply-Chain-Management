/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supplychain.supplychain.service.impl;

import com.supplychain.supplychain.entity.User;
import com.supplychain.supplychain.exception.BadRequestException;
import com.supplychain.supplychain.exception.NotFoundException;
import com.supplychain.supplychain.form.ChangePasswordForm;
import com.supplychain.supplychain.form.LoginForm;
import com.supplychain.supplychain.form.UserForm;
import com.supplychain.supplychain.repository.SupplierRepository;
import com.supplychain.supplychain.repository.UserRepository;
import static com.supplychain.supplychain.security.AccessTokenUserDetailsService.PURPOSE_ACCESS_TOKEN;
import com.supplychain.supplychain.security.config.SecurityConfig;
import com.supplychain.supplychain.security.util.InvalidTokenException;
import com.supplychain.supplychain.security.util.SecurityUtil;
import com.supplychain.supplychain.security.util.TokenExpiredException;
import com.supplychain.supplychain.security.util.TokenGenerator;
import com.supplychain.supplychain.security.util.TokenGenerator.Status;
import com.supplychain.supplychain.security.util.TokenGenerator.Token;
import com.supplychain.supplychain.service.UserService;
import com.supplychain.supplychain.view.LoginView;
import com.supplychain.supplychain.view.SupplierView;
import com.supplychain.supplychain.view.UserView;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;


@Service
public class UserServiceImpl implements UserService {

    private static final String PURPOSE_REFRESH_TOKEN = "REFRESH_TOKEN";

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private TokenGenerator tokenGenerator;

    @Autowired
    private SecurityConfig securityConfig;

    @Override
    public UserView add(UserForm form) {
        return new UserView(userRepository.save(new User(
                form.getName(),
                form.getEmail(),
                passwordEncoder.encode(form.getPassword()),
                form.getRole()
                
        )));
    }

    @Override
    public UserView currentUser() {
        return new UserView(
                userRepository.findById(SecurityUtil.getCurrentUserId()).orElseThrow(NotFoundException::new)
        );
    }


    // @Override
    // public UserView currentPurchaser() {
    //     return new UserView(

        
    //             userRepository.findById(SecurityUtil.getCurrentUserId()).orElseThrow(NotFoundException::new)
    //     );
    // }




    @Override
    public LoginView login(LoginForm form, Errors errors) throws BadRequestException {
        if (errors.hasErrors()) {
            throw badRequestException();
        }
        User user = userRepository.findByEmail(form.getEmail()).orElseThrow(UserServiceImpl::badRequestException);
        Byte role=userRepository.findRoleByEmail(form.getEmail());
        if (!passwordEncoder.matches(form.getPassword(), user.getPassword())) {
            throw badRequestException();
        }
        if(user.getRole()!=role){
            throw badRequestException();
        }
        String id = String.format("%010d", user.getUserId());
        Token accessToken = tokenGenerator.create(PURPOSE_ACCESS_TOKEN, id, securityConfig.getAccessTokenExpiry());
        Token refreshToken = tokenGenerator.create(PURPOSE_REFRESH_TOKEN, id + user.getPassword(), securityConfig.getRefreshTokenExpiry());
        return new LoginView(user, accessToken, refreshToken);
    }

    @Override
    public LoginView refresh(String refreshToken) throws BadRequestException {
        Status status;
        try {
            status = tokenGenerator.verify(PURPOSE_REFRESH_TOKEN, refreshToken);
        } catch (InvalidTokenException e) {
            throw new BadRequestException("Invalid token", e);
        } catch (TokenExpiredException e) {
            throw new BadRequestException("Token expired", e);
        }

        int userId;
        try {
            userId = Integer.parseInt(status.data.substring(0, 10));
        } catch (NumberFormatException e) {
            throw new BadRequestException("Invalid token", e);
        }

        String password = status.data.substring(10);

        User user = userRepository.findByUserIdAndPassword(userId, password).orElseThrow(UserServiceImpl::badRequestException);

        String id = String.format("%010d", user.getUserId());
        Token accessToken = tokenGenerator.create(PURPOSE_ACCESS_TOKEN, id, securityConfig.getAccessTokenExpiry());
        return new LoginView(
                user,
                new LoginView.TokenView(accessToken.value, accessToken.expiry),
                new LoginView.TokenView(refreshToken, status.expiry)
        );
    }

    private static BadRequestException badRequestException() {
        return new BadRequestException("Invalid credentials");
    }

    @Override
    public Collection<User> list() {
        return userRepository.findAll();
    }
    @Override
    public Collection<User> listSupplier() {
      
        return userRepository.findUsers(1);
    }

    @Override
    public Collection<User> listPurchaser() {
      
        return userRepository.findUsers(2);
    }

    @Override
    @Transactional
    public UserView update(Integer userId, UserForm form) throws NotFoundException {
        return userRepository.findById(userId)
                .map((user) -> {
                    return new UserView(userRepository.save(user.update(form)));
                }).orElseThrow(NotFoundException::new);
    }
    
    @Override
    @Transactional
    public UserView updateLoggedUser(UserForm form){
        
        User user=userRepository.findByUserId(SecurityUtil.getCurrentUserId());
                
                    return new UserView(userRepository.save(user.update(form)));
                
    }

    @Override
    @Transactional
    public void delete(Integer userId) throws NotFoundException {
        userRepository.delete(
                userRepository.findById(userId)
                        .orElseThrow(NotFoundException::new)
        );
    }

    @Override
    public User getCurrentUser() {
     return  userRepository.findByUserId(SecurityUtil.getCurrentUserId());
    }

    @Override
    public UserView getUser(Integer userId) {
     return new UserView(userRepository.findByUserId(userId)) ;
    }


    

    @Override
    @Transactional
    public UserView updatepassword(ChangePasswordForm form) throws NotFoundException {
        User user = userRepository.findById(SecurityUtil.getCurrentUserId())
                .orElseThrow(UserServiceImpl::badRequestException);


        if (!passwordEncoder.matches(form.getOldpassword(), user.getPassword())) {
           
            throw badRequestException();
        }

        if(form.getNewpassword().equals(form.getConfirmpassword()))

         {
            user.setPassword(passwordEncoder.encode(form.getConfirmpassword()));
            userRepository.save(user);

        } else {
            throw badRequestException();
        }

        return new UserView(user);
    }




}
