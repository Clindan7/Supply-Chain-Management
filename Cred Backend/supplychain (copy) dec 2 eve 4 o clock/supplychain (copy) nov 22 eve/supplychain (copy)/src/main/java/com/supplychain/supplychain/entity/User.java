
package com.supplychain.supplychain.entity;

import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Where;

import com.supplychain.supplychain.form.UserForm;


@Entity
@Where(clause="status=1")

public class User {

    public static enum Status {
        INACTIVE((byte) 0),
        ACTIVE((byte) 1);

        public final byte value;

        private Status(byte value) {
            this.value = value;
        }
    }
    public static enum Role {
        DeliveryPerson((byte) 3),
        PURCHASER((byte) 2),
        SUPPLIER((byte) 1),
        ADMIN((byte) 0);

        public final byte value;

        private Role(byte value) {
            this.value = value;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String name;
    private String email;
    private String password;
    private byte status;
    private byte role;
 
 


    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;


    public User() {
    }

    public User(Integer userId) {
        this.userId = userId;
    }

    public User(String name, String email, String password, byte role) {
        this.name = name;
        this.email = email;
        this.password = password;

        this.status = Status.ACTIVE.value;
        
        if (role==0){
            this.role= Role.ADMIN.value;
        }else if(role==1){
            this.role=Role.SUPPLIER.value;
        }
    else if(role==2){
        this.role=Role.PURCHASER.value;
    }
        else{
            this.role=Role.DeliveryPerson.value;
        }
        Date dt = new Date();
        this.createDate = dt;
        this.updateDate = dt;
    }

    public User update(UserForm form) {

        this.name=form.getName();
        this.email = form.getEmail();
        this.password=form.getPassword();
        this.role=form.getRole();

        return this;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public byte getRole() {
       return role;
    }

    public void setRole(byte role) {
        this.role = role;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        return Objects.equals(this.userId, other.userId);
    }

    @Override
    public String toString() {
        return "com.supplychain.supplychain.entity.User[ userId=" + userId + " ]";
    }
}
