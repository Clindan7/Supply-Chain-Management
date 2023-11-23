package com.supplychain.supplychain.view;



import com.supplychain.supplychain.entity.Item;

public class ItemDetailView extends ItemListView {

    public ItemDetailView(Item item) {
        super(
            item.getItemId(),
             item.getItemName(),
              item.getDescription(), 
              item.getType(),
              item.getStatus(),
              item.getPhotos(),
              item.getCreateDate(), 
              item.getUpdateDate());
    }
    
}
