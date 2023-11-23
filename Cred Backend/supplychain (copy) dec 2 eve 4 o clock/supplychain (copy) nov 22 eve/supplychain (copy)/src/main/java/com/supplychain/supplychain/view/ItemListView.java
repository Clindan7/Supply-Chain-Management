package com.supplychain.supplychain.view;
import java.util.Date;

import com.supplychain.supplychain.json.Json;

public class ItemListView {
    
    private final int itemId;
    private final String itemName;
    private final String description;
    private final String type;
    private  String photos;

    private final byte status;
    @Json.DateTimeFormat
    private final Date createDate;

    public ItemListView(int itemId, String itemName, String description,
     String type, byte status,String photos, Date createDate,
            Date updateDate) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.description = description;
        this.type = type;
        
        this.status = status;
        this.photos=photos;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }
    @Json.DateTimeFormat
    private final Date updateDate;
    public int getItemId() {
        return itemId;
    }
    public String getItemName() {
        return itemName;
    }
    public String getDescription() {
        return description;
    }
    public String getType() {
        return type;
    }
    public byte getStatus() {
        return status;
    }

    public String getPhotos() {
        return photos;
    }
    public Date getCreateDate() {
        return createDate;
    }
    public Date getUpdateDate() {
        return updateDate;
    }
}
