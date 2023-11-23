/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supplychain.supplychain.view;

import com.supplychain.supplychain.entity.User;
import com.supplychain.supplychain.json.Json;
import java.util.Date;

/**
 *
 * @author nirmal
 */
public class UserView {

    private final int userId;
    private final String name;
    private final String email;
    private final String password;
    private final short status;
    private final byte role;
    @Json.DateTimeFormat
    private final Date createDate;
    @Json.DateTimeFormat
    private final Date updateDate;

    public UserView(User user) {
        this.userId = user.getUserId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.password=user.getPassword();
        this.status = user.getStatus();
        this.role=user.getRole();
        this.createDate = user.getCreateDate();
        this.updateDate = user.getUpdateDate();
    }


    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public short getStatus() {
        return status;
    }

    public short getRole() {
        return role;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }


    public String getPassword() {
        return password;
    }
}
