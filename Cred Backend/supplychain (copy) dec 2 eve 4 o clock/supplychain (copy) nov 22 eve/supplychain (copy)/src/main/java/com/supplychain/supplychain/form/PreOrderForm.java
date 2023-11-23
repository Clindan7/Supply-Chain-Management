package com.supplychain.supplychain.form;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


public class PreOrderForm {
    
 
    @Size(max = 30)
    private String address;

private Integer quantity;
private Integer itemId;
private Integer supplierId;
private Integer dPersonId;


public Integer getdPersonId() {
    return dPersonId;
}
public void setdPersonId(Integer dPersonId) {
    this.dPersonId = dPersonId;
}
private Date dDate;
public Date getdDate() {
    return dDate;
}
public void setdDate(Date dDate) {
    this.dDate = dDate;
}
public Integer getItemId() {
    return itemId;
}
public void setItemId(Integer itemId) {
    this.itemId = itemId;
}
public Integer getSupplierId() {
    return supplierId;
}
public void setSupplierId(Integer supplierId) {
    this.supplierId = supplierId;
}



 
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
 
  

   
}
