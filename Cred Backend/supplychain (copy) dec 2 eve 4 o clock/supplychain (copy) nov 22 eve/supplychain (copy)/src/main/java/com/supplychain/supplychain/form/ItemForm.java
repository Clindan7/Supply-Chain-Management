package com.supplychain.supplychain.form;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
public class ItemForm {
    
    @Size(max = 30)
    @NotBlank
    private String itemName;
    // @Size(max = 250)
    private String description;
    @Size(max = 30)
    private String type;
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
   
}
