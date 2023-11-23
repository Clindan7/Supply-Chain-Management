package com.supplychain.supplychain.entity;
import java.util.Date;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Where;

import com.supplychain.supplychain.form.PreOrderForm;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;


@Entity
@Where(clause="status=1")
public class PreOrder {


    public static enum Status {
        // DELETED((byte) 0),
        INACTIVE((byte) 0),
        ACTIVE((byte) 1);

        public final byte value;

        private Status(byte value) {
            this.value = value;
        }

    }
                                                                               
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer preOrderId;
    private String address;
    private Integer quantity;
    private Date dDate;
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private User user;
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private Supplier supplier;
     @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private Item item;
    @ManyToOne(optional = true, fetch = FetchType.LAZY,cascade=CascadeType.ALL)
    private DeliveryPerson deliveryPerson;
    private byte status;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    public PreOrder(){

    }

    public PreOrder(PreOrderForm form,DeliveryPerson deliveryPerson, Integer userId) {

        this.user = new User(userId);
        this.supplier = new Supplier(form.getSupplierId());
        this.item = new Item(form.getItemId());
        this.address = form.getAddress();
        this.quantity = form.getQuantity();
        this.dDate=form.getdDate();
     
      this.deliveryPerson=deliveryPerson;

        this.status = Status.ACTIVE.value;
        // this.role= Role.SUPPLIER.value;
        Date dt=new Date();
        this.createDate = dt;
        this.updateDate = dt;
    }

    public PreOrder(PreOrderForm form, Integer userId) {

        this.user = new User(userId);
        this.supplier = new Supplier(form.getSupplierId());
        this.item = new Item(form.getItemId());
        this.address = form.getAddress();
        this.quantity = form.getQuantity();
        this.dDate=form.getdDate();
    
        this.status = Status.ACTIVE.value;
        // this.role= Role.SUPPLIER.value;
        Date dt=new Date();
        this.createDate = dt;
        this.updateDate = dt;
    }

    public PreOrder update(PreOrderForm form, DeliveryPerson deliveryPerson) {
        this.address=form.getAddress();
          this.quantity = form.getQuantity();
         this.dDate=form.getdDate();
         this.deliveryPerson=deliveryPerson;
         
  
          Date dt = new Date();
          this.updateDate = dt;
  
          return this;
      }

      public PreOrder update(PreOrderForm form) {
        this.address=form.getAddress();
          this.quantity = form.getQuantity();
         this.dDate=form.getdDate();
          Date dt = new Date();
          this.updateDate = dt;
  
          return this;
      }



    public PreOrder(Integer preOrderId) {
        this.preOrderId = preOrderId;
    }

    public Integer getPreOrderId() {
        return preOrderId;
    }


    public void setPreOrderId(Integer preOrderId) {
        this.preOrderId = preOrderId;
    }


    public Item getItem() {
        return item;
    }


    public void setItem(Item item) {
        this.item = item;
    }


    public Supplier getSupplier() {
        return supplier;
    }


    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }


    public User getUser() {
        return user;
    }


    public void setUser(User user) {
        this.user = user;
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


    public byte getStatus() {
        return status;
    }


    public void setStatus(byte status) {
        this.status = status;
    }


    public DeliveryPerson getDeliveryPerson() {
        return deliveryPerson;
    }


    public void setDeliveryPerson(DeliveryPerson deliveryPerson) {
        this.deliveryPerson = deliveryPerson;
    }

    public byte getOrderStatus() {
        return status;
    }


    public void setOrderStatus(byte status) {
        this.status = status;
    }



    public Date getdDate() {
        return dDate;
    }

    public void setdDate(Date dDate) {
        this.dDate = dDate;
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
        hash += (preOrderId != null ? preOrderId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PreOrder)) {
            return false;
        }
        PreOrder other = (PreOrder) object;
        return Objects.equals(this.preOrderId, other.preOrderId);
    }

    @Override
    public String toString() {
        return "com.supplychain.supplychain.entity.PreOrder[ preOrderId=" + preOrderId + " ]";
    }
}

    






    
//     public Item update(ItemForm form) {
//         this.itemName = form.getItemName();
//         this.description = form.getDescription();
//         this.type = form.getType();

//         Date dt = new Date();
//         this.updateDate = dt;

//         return this;
//     }

    
//     public Item(ItemForm form, Integer userId) {

//         this.user = new User(userId);

//         this.itemName = form.getItemName();
//         this.description = form.getDescription();
//         this.type = form.getType();
//         this.status = Status.ACTIVE.value;

//         Date dt = new Date();
//         this.createDate = dt;
//         this.updateDate = dt;
//     }



   












   


