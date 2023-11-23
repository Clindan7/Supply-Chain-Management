package com.supplychain.supplychain.view;
import java.util.Date;

import com.supplychain.supplychain.entity.DeliveryPerson;
import com.supplychain.supplychain.json.Json;
public class DeliveryPersonView {
     
    private final int dPersonId;
    private final String dPersonName ;
    // private final String location;
    private final String mobile;
    private final String route;
    private final byte status;
    // private final byte role;
    @Json.DateTimeFormat
    private final Date createDate;
    @Json.DateTimeFormat
    private final Date updateDate;

    public DeliveryPersonView(DeliveryPerson deliveryPerson) {
        this.dPersonId = deliveryPerson.getdPersonId();
        this.dPersonName = deliveryPerson.getdPersonName();
        // this.location = deliveryPerson.getLocation();
        this.mobile = deliveryPerson.getMobile();
        this.route= deliveryPerson.getRoute();
        this.status = deliveryPerson.getStatus();
        // this.role=supplier.getRole();
        this.createDate = deliveryPerson.getCreateDate();
        this.updateDate = deliveryPerson.getUpdateDate();
    }

    public int getdPersonId() {
        return dPersonId;
    }

    public String getdPersonName() {
        return dPersonName;
    }

  
    public String getMobile() {
        return mobile;
    }

    public String getRoute() {
        return route;
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
    

}
