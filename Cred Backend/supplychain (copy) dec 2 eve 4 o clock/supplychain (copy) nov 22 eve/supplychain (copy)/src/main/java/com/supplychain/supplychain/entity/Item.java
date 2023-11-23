package com.supplychain.supplychain.entity;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Where;

import com.supplychain.supplychain.form.ItemForm;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.Column;

@Entity
@Where(clause="status=1")

public class Item {

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
    private Integer itemId;
    private String itemName;
    private String description;
    private String type;
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private User user;
    private byte status;
    @Column(nullable = true, length = 64)
	private String photos;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
    


    public Item update(ItemForm form) {
        this.itemName = form.getItemName();
        this.description = form.getDescription();
        this.type = form.getType();
        Date dt = new Date();
        this.updateDate = dt;

        return this;
    }


    
    public Item(ItemForm form, Integer userId) {

        this.user = new User(userId);
    
        this.itemName = form.getItemName();
        this.description = form.getDescription();
        this.type = form.getType();
        this.status = Status.ACTIVE.value;

        Date dt = new Date();
        this.createDate = dt;
        this.updateDate = dt;
    }

    public Item(String itemName, String description, String type,Integer userId) {
        
        this.itemName = itemName;
        this.description = description;
        this.type = type;
        this.status = Status.ACTIVE.value;
        Date dt = new Date();
        this.createDate = dt;
        this.updateDate = dt;

        this.user = new User(userId);
       
    
    }
      


    public Item(){

    }


    public Item(Integer itemId) {
        this.itemId = itemId;
    }
    
    public String getPhotos() {
        return photos;
    }



    public void setPhotos(String photos) {
        this.photos = photos;
    }



    public Integer getItemId() {
        return itemId;
    }



    public void setItemId(int itemId) {
        this.itemId = itemId;
    }



    public String getItemName() {
        return itemName;
    }



    public void setItemName(String itemName) {
        this.itemName = itemName;
    }



    public String getDescription() {
        return description;
    }



    public void setDescription(String description) {
        this.description = description;
    }



    public String getType() {
        return type;
    }



    public void setType(String type) {
        this.type = type;
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


    @Transient
	public String getPhotosImagePath() {
		if (photos == null || itemId == null)
			return null;
		return "/items - photos/" + itemId + photos;
	}


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemId != null ? itemId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Item)) {
            return false;
        }
        Item other = (Item) object;
        return Objects.equals(this.itemId, other.itemId);
    }

    @Override
    public String toString() {
        return "com.supplychain.supplychain.entity.Item[ itemId=" + itemId + " ]";
    }



    public Item orElseThrow(Item object) {
        return null;
    }

   
}

