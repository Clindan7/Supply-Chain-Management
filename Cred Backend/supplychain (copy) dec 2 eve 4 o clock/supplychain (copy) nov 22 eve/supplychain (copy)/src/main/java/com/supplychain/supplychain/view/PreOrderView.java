package com.supplychain.supplychain.view;
import java.util.Date;

import com.supplychain.supplychain.entity.PreOrder;
import com.supplychain.supplychain.json.Json;

public class PreOrderView {
    
    private  Integer preOrderId;
    private Integer itemId;
    private final String address ;
    private final Integer quantity;
    private final Date dDate;





    private final byte status;
    // private final byte role;
    @Json.DateTimeFormat
    private final Date createDate;
    @Json.DateTimeFormat
    private final Date updateDate;

    public int getPreorderId() {
        return preOrderId;
    }

    public int getItemId() {
        return itemId;
    }

    public int getPreOrderId() {
        return preOrderId;
    }

    public String getAddress() {
        return address;
    }
    public int getQuantity() {
        return quantity;
    }
 
    public byte getStatus() {
        return status;
    }
    public Date getCreateDate() {
        return createDate;
    }
    public Date getUpdateDate() {
        return updateDate;
    }




    public PreOrderView(PreOrder preorder) {
        this.preOrderId = preorder.getPreOrderId();
        this.address = preorder.getAddress();
        this.quantity = preorder.getQuantity();
        this.dDate=preorder.getdDate();
        this.status = preorder.getStatus();
      
        this.itemId=preorder.getItem().getItemId();
        // this.role=supplier.getRole();
        this.createDate = preorder.getCreateDate();
        this.updateDate = preorder.getUpdateDate();
    }
    
    
  


}
