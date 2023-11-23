package com.supplychain.supplychain.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.supplychain.supplychain.entity.Item;
import com.supplychain.supplychain.exception.NotFoundException;
import com.supplychain.supplychain.form.ItemForm;
import com.supplychain.supplychain.repository.ItemRepository;
import com.supplychain.supplychain.security.util.SecurityUtil;
import com.supplychain.supplychain.service.ItemService;
import com.supplychain.supplychain.util.FileUtil;
import com.supplychain.supplychain.view.ItemDetailView;
import com.supplychain.supplychain.view.ItemListView;




@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Collection<ItemListView> list() {
        return itemRepository.findAllByUserUserIdAndStatus(SecurityUtil.getCurrentUserId(),Item.Status.ACTIVE.value);
    }

    @Override
    public Collection<Item> listAll() {
        return itemRepository.findAll();
    }


    @Override
    public Collection<Item> listByPost() {
        return itemRepository.findByPost();
    }



@Override
    public List<Item> findPaginated(int pageNo, int pageSize) {

        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<Item> pagedResult = itemRepository.findAll(paging);

        return pagedResult.toList();
    }

    @Override
    public Collection<Item> listAllBySupplier() {
        return itemRepository.findAllByUserUserId(SecurityUtil.getCurrentUserId());
    }

    @Override
    public ItemDetailView add(ItemForm form) {
        System.out.println("user id : "+SecurityUtil.getCurrentUserId());
        return new ItemDetailView(itemRepository.save(new Item(form, SecurityUtil.getCurrentUserId())));
    }

    @Override
    public ItemDetailView get(Integer itemId) throws NotFoundException {
        return itemRepository.findByItemIdAndUserUserId(itemId, SecurityUtil.getCurrentUserId())
                .map((item) -> {
                    return new ItemDetailView(item);
                }).orElseThrow(NotFoundException::new);
    }


    @Override
    public List<ItemDetailView> getArray(Integer itemId) throws NotFoundException {
        System.out.println("<<<<<<<<<<<<<>>>>hoooooiiiiiii>>>>>>>>>>>>>>>>>>>>>");
        return itemRepository.findByItemIdAndUserUserId(itemId, SecurityUtil.getCurrentUserId())
                .stream().map((item) -> {
                    return new ItemDetailView(item);
                }).toList();
    }

    @Override
    @Transactional
    public ItemDetailView update(Integer itemId, ItemForm form) throws NotFoundException {
     
        return itemRepository.findByItemIdAndUserUserId(itemId, SecurityUtil.getCurrentUserId())
                .map((item) -> {
                    return new ItemDetailView(itemRepository.save(item.update(form)));
                }).orElseThrow(NotFoundException::new);
    }


    @Override
    @Transactional
    public void delete(Integer itemId) throws NotFoundException {
        Item item=itemRepository.findByItemIdAndStatus(itemId,Item.Status.ACTIVE.value);
        
        item.setStatus(Item.Status.INACTIVE.value);
        itemRepository.save(item);
        return;
                
    }


    @Override
    public HttpEntity<byte[]> getProfilePic(Integer userId) {

        String profilePic = itemRepository.findByItemId(SecurityUtil.getCurrentUserId())
                .getPhotos();

        byte[] file = FileUtil.getProfilePic(profilePic);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        headers.setContentLength(file.length);

        return new HttpEntity<>(file, headers);

    }


    @Override
    public void saveCollection(List<Integer> arr) {
        System.out.println("333333333333333333333333333333333333"+arr);

 
          
         }
    

         @Override
         public Collection<Item> list(String itemName,String filterItem) {
             System.out.println(">>>>>>>>>>>>>>>>----------------------");
             System.out.println(filterItem);
     
             if((itemName == null ) && (filterItem == null)){
                 return itemRepository.findAll();
             }
            
             else if((itemName != null ) && (filterItem == null)){
                 return itemRepository.findByItemNameStartsWith(itemName);
             }
             else if((itemName == null ) && (filterItem != null)){
                 System.out.println("insideeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
                 System.out.println(filterItem);
                 System.out.println("a-z");
                 if(filterItem.equals("a-z")){
                     System.out.println("11111111111111111111111111111111111");
                     return itemRepository.findAllByOrderByItemNameAsc();
                 }
                 else if(filterItem.equals("z-a")){
                     return itemRepository.findAllByOrderByItemNameDesc();
                 }
                 else if(filterItem.equals("latest-old")){
                     return itemRepository.findAllByOrderByCreateDateDesc();
                 }
                 else{
     
                     return itemRepository.findAllByOrderByCreateDateAsc();
                 }
             }
           
             else{
                 if(filterItem.equals("a-z")){
                     return itemRepository.findByItemNameStartsWithOrderByItemNameAsc(itemName);
                 }
                 else if(filterItem.equals("z-a")){
                     System.out.println("hhhhhhhhhhhhheeeeeeeeeeeeeeeeerrrrrrrrrrrrrrrrrrrrrrrrrrrrreeeeeeeeee-----------");
                     return itemRepository.findByItemNameStartsWithOrderByItemNameDesc(itemName);
                 }
                 else if(filterItem.equals("latest-old")){
                     
                     return itemRepository.findByItemNameStartsWithOrderByCreateDateDesc(itemName);
                 }
                 else{
     
                     return itemRepository.findByItemNameStartsWithOrderByCreateDateAsc(itemName);
                 }
            }     
         }


    }
    
