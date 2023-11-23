package com.supplychain.supplychain.view;
import java.util.Date;

import com.supplychain.supplychain.entity.Supplier;
import com.supplychain.supplychain.json.Json;

public class SupplierView {
    
    private final int supplierId;
    private final String supplierName ;
    private final String location;
    private final String mobile;
    private final byte status;
    // private final byte role;
    @Json.DateTimeFormat
    private final Date createDate;
    @Json.DateTimeFormat
    private final Date updateDate;

    public SupplierView(Supplier supplier) {
        this.supplierId = supplier.getSupplierId();
        this.supplierName = supplier.getSupplierName();
        this.location = supplier.getLocation();
        this.mobile = supplier.getMobile();
        this.status = supplier.getStatus();
        // this.role=supplier.getRole();
        this.createDate = supplier.getCreateDate();
        this.updateDate = supplier.getUpdateDate();
    }


    public int getSupplierId() {
        return supplierId;
    }
    public String getSupplierName() {
        return supplierName;
    }
    public String getLocation() {
        return location;
    }
    public String getMobile() {
        return mobile;
    }
    public byte getStatus() {
        return status;
    }

    // public byte getRole() {
    //     return role;
    // }
    public Date getCreateDate() {
        return createDate;
    }
    public Date getUpdateDate() {
        return updateDate;
    }
}
