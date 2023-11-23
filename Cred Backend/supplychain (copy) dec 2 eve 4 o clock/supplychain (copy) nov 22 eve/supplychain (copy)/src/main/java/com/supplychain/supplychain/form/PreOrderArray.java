package com.supplychain.supplychain.form;

import java.util.List;

import javax.validation.constraints.Size;

public class PreOrderArray {
    
    @Size(max = 30)
    private String address;

private List<Integer> quantity;

private List<Integer> itemId;
private Integer supplierId;
private Integer dPersonId;



public String getAddress() {
    return address;
}
public void setAddress(String address) {
    this.address = address;
}
public List<Integer> getQuantity() {
    return quantity;
}
public void setQuantity(List<Integer> quantity) {
    this.quantity = quantity;
}

public Integer getSupplierId() {
    return supplierId;
}
public void setSupplierId(Integer supplierId) {
    this.supplierId = supplierId;
}
public Integer getdPersonId() {
    return dPersonId;
}
public void setdPersonId(Integer dPersonId) {
    this.dPersonId = dPersonId;
}
public List<Integer> getItemId() {
    return itemId;
}
public void setItemId(List<Integer> itemId) {
    this.itemId = itemId;
}


}
