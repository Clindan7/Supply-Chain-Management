package com.supplychain.supplychain.form;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import com.supplychain.supplychain.form.validation.Password;

public class UserForm {

    @NotBlank
    @Size(max = 50)
    private String name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
