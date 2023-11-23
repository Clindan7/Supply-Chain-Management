
package com.supplychain.supplychain.entity;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import com.supplychain.supplychain.form.PostOrderForm;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;

   
@Entity
// @Where(clause="status=1")

public class PostOrder {


    public static enum Status {
       
        INACTIVE((byte) 0),
        ACTIVE((byte) 1);

        public final byte value;

        private Status(byte value) {
            this.value = value;
        }
    }
        public static enum OrderStatus {
        
            REQUESTED((byte) 0),
            ACCEPTED((byte) 1),
        DELIVERYPERSON((byte) 2),
        SHIPPED((byte) 3),
        DELIVERED((byte) 4);


        
    
            public final byte value;
    
            private OrderStatus(byte value) {
                this.value = value;
            }


           
    
        }
    
                                                                               
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postOrderId;
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private PreOrder preOrder;

    private Integer price;

    private byte status;


    public byte getStatus() {
        return status;
    }


    public void setStatus(byte status) {
        this.status = status;
    }


    public PostOrder(){

    }


    public PostOrder(PostOrderForm form, Integer preOrderId) {

        this.preOrder = new PreOrder(form.getPreOrderId());
        this.price = form.getPrice();
        this.orderStatus=OrderStatus.REQUESTED.value;
        this.status = Status.ACTIVE.value;
     
    }

    public PostOrder update(PostOrderForm form) {
        this.price=form.getPrice(); 
  
          return this;
      }

      public PostOrder BulkShip(PostOrderForm form){
        this.orderStatus=form.getOrderStatus();
        return this;
      }
    

      public PreOrder getPreOrder() {
        return preOrder;
    }


    public void setPreOrder(PreOrder preOrder) {
        this.preOrder = preOrder;
    }


    public Integer getPostOrderId() {
        return postOrderId;
    }


    public void setPostOrderId(Integer postOrderId) {
        this.postOrderId = postOrderId;
    }



    public Integer getPrice() {
        return price;
    }


    public void setPrice(Integer price) {
        this.price = price;
    }


    private byte orderStatus;


    public byte getOrderStatus() {
        return orderStatus;
    }


    public void setOrderStatus(byte orderStatus) {
        this.orderStatus = orderStatus;
    }

    public PreOrder getPreorder() {
        return preOrder;
    }


    public void setPreorder(PreOrder preOrder) {
        this.preOrder = preOrder;
    }




    @Override
    public int hashCode() {
        int hash = 0;
        hash += (postOrderId != null ? postOrderId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PostOrder)) {
            return false;
        }
        PostOrder other = (PostOrder) object;
        return Objects.equals(this.postOrderId, other.postOrderId);
    }

    @Override
    public String toString() {
        return "com.supplychain.supplychain.entity.PostOrder[ postOrderId=" + postOrderId + " ]";
    }



}














   



// }