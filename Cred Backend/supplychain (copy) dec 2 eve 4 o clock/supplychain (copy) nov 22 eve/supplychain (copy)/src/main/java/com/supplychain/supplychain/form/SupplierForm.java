package com.supplychain.supplychain.form;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


public class SupplierForm {
    
    @Size(max = 30)
    @NotBlank
    private String supplierName;
    @Size(max = 200)
    private String location;
    @Size(max = 30)
    private String mobile;
    private Integer userId;
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
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
   
}
