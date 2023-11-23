/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supplychain.supplychain.form;
import com.supplychain.supplychain.form.validation.Password;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


public class LoginForm {

    @NotBlank
    @Size(max = 255)
    @Email
    private String email;
    @Password
    private String password;
    
    private byte role;

    public byte getRole() {
        return role;
    }

    public void setRole(byte role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
