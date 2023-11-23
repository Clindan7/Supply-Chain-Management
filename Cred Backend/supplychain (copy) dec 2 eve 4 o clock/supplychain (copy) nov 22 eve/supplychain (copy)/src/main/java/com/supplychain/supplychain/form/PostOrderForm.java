package com.supplychain.supplychain.form;


public class PostOrderForm {
    

    private Integer price;

    private Integer preOrderId;


    private byte orderStatus;


    public byte getOrderStatus() {
        return orderStatus;
    }
    public void setOrderStatus(byte orderStatus) {
        this.orderStatus = orderStatus;
    }

    
    public Integer getPreOrderId() {
        return preOrderId;
    }
    public void setPreOrderId(Integer preOrderId) {
        this.preOrderId = preOrderId;
    }
    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }
   
 
  

   
}