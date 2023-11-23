package com.supplychain.supplychain.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import com.supplychain.supplychain.form.DeliveryForm;


@Entity
// @Where(clause="status=1")
public class DeliveryPerson {

    public static enum Status {
        INACTIVE((byte) 0),
        ACTIVE((byte) 1);

        public final byte value;

        private Status(byte value) {
            this.value = value;
        }
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer dPersonId;
    private String dPersonName;
    private String mobile;
    private String route;
    @OneToOne(optional = true, fetch = FetchType.LAZY)
    private User user;
    private byte status;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
     
    
    public DeliveryPerson(Integer dPersonId, String dPersonName, String mobile, String route,
    User user, byte status, Date createDate, Date updateDate) {
this.dPersonId = dPersonId;
this.dPersonName = dPersonName;
this.mobile = mobile;
this.route = route;
this.user = user;
this.status = Status.ACTIVE.value;
this.createDate = createDate;
this.updateDate = updateDate;
}

public DeliveryPerson(DeliveryForm form) {

    this.user = new User(form.getUserId());
    this.dPersonName = form.getdPersonName();
    this.mobile = form.getMobile();
    this.route=form.getRoute();
    this.status = Status.ACTIVE.value;
    Date dt=new Date();
    this.createDate = dt;
    this.updateDate = dt;
}

public DeliveryPerson update(DeliveryForm form) {
    this.dPersonName=form.getdPersonName();
      this.mobile=form.getMobile();
      this.route=form.getRoute();
      Date dt = new Date();
      this.updateDate = dt;

      return this;
  }

  public DeliveryPerson() {
}


public Integer getdPersonId() {
    return dPersonId;
}
public void setdPersonId(Integer dPersonId) {
    this.dPersonId = dPersonId;
}
public String getdPersonName() {
    return dPersonName;
}
public void setdPersonName(String dPersonName) {
    this.dPersonName = dPersonName;
}

public String getMobile() {
    return mobile;
}
public void setMobile(String mobile) {
    this.mobile = mobile;
}
public String getRoute() {
    return route;
}
public void setRoute(String route) {
    this.route = route;
}
public byte getStatus() {
    return status;
}
public void setStatus(byte status) {
    this.status = status;
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


public DeliveryPerson(Integer dPersonId) {
    this.dPersonId = dPersonId;
}


public User getUser() {
    return user;
}
public void setUser(User user) {
    this.user = user;
}


}
