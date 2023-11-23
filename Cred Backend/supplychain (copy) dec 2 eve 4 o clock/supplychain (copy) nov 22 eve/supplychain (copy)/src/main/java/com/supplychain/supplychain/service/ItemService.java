package com.supplychain.supplychain.service;
import java.util.Collection;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;

import com.supplychain.supplychain.entity.Item;
import com.supplychain.supplychain.exception.NotFoundException;
import com.supplychain.supplychain.form.ItemForm;
import com.supplychain.supplychain.view.ItemDetailView;
import com.supplychain.supplychain.view.ItemListView;

public interface ItemService {
    
    Collection<ItemListView> list();

    ItemDetailView add(ItemForm form);

    ItemDetailView get(Integer itemId) throws NotFoundException;

    // List<Item> listAllItems(Integer pageNo, Integer pageSize, String sortBy);

    ItemDetailView update(Integer itemId, ItemForm form) throws NotFoundException;

    void delete(Integer itemId) throws NotFoundException;

    Collection<Item> listAll();

    
    
    Collection<Item>listByPost();

    Collection<Item> listAllBySupplier();
    // Collection<Item> listAllItemSupplier();

    public HttpEntity<byte[]> getProfilePic(Integer userId) throws NotFoundException;

    List<ItemDetailView> getArray(Integer itemId);


    void saveCollection(List<Integer> arr);

    List<Item> findPaginated(int pageNo, int pageSize);

    Collection<Item> list(String itemName,String filterItem);

    
}
