package com.supplychain.supplychain.view;
import java.util.Date;

import com.supplychain.supplychain.entity.PostOrder;
import com.supplychain.supplychain.entity.PreOrder;
import com.supplychain.supplychain.json.Json;

public class PostOrderView {
    
    private final Integer postOrderId;

    private final Integer price ;

    private PreOrder preOrder;

    // private final byte orderStatus;
   

   
    private final byte status;

    // public byte getOrderStatus() {
    //     return orderStatus;
    // }

    public byte getStatus() {
        return status;
    }

    public Integer getPostOrderId() {
        return postOrderId;
    }

    public Integer getPrice() {
        return price;
    }
  





    public PostOrderView(PostOrder postOrder) {
        this.postOrderId = postOrder.getPostOrderId();
        this.price = postOrder.getPrice();
        this.preOrder=postOrder.getPreorder();
        // this.orderStatus=postOrder.getOrderStatus();
        this.status = postOrder.getStatus();
      
    }

    public PreOrder getPreOrder() {
        return preOrder;
    }

   
    
    
  


}