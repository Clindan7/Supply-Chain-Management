package com.supplychain.supplychain.entity;
import java.util.Date;
import java.util.Objects;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Where;

import com.supplychain.supplychain.form.SupplierForm;

    
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;

@Entity
@Where(clause="status=1")

public class Supplier {

    public static enum Status {
        // DELETED((byte) 0),
        INACTIVE((byte) 0),
        ACTIVE((byte) 1);

        public final byte value;

        private Status(byte value) {
            this.value = value;
        }
    }


    public static enum Role {
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
    private Integer supplierId;
    private String supplierName;
    private String location;
    private String mobile;
    @OneToOne(optional = true, fetch = FetchType.LAZY)
    private User user;
    private byte status;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

   


    public Supplier(SupplierForm form) {

        this.user = new User(form.getUserId());
        this.supplierName = form.getSupplierName();
        this.location = form.getLocation();
        this.mobile = form.getMobile();

        this.status = Status.ACTIVE.value;
        // this.role= Role.SUPPLIER.value;
        Date dt=new Date();
        this.createDate = dt;
        this.updateDate = dt;
    }

    public Supplier update(SupplierForm form) {
      this.supplierName=form.getSupplierName();
        this.location = form.getLocation();
        this.mobile=form.getMobile();

        Date dt = new Date();
        this.updateDate = dt;

        return this;
    }


    public Integer getSupplierId() {
        return supplierId;
    }
    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }
    public String getSupplierName() {
        return supplierName;
    }
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
 
    public byte getStatus() {
        return status;
    }
    public void setStatus(byte status) {
        this.status = status;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public Date getUpdateDate() {
        return updateDate;
    }
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public Supplier() {
    }


    public Supplier(Integer supplierId) {
        this.supplierId = supplierId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (supplierId != null ? supplierId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Supplier)) {
            return false;
        }
        Supplier other = (Supplier) object;
        return Objects.equals(this.supplierId, other.supplierId);
    }

    @Override
    public String toString() {
        return "com.supplychain.supplychain.entity.Supplier[ supplierId=" + supplierId + " ]";
    }
}








