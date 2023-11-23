package com.supplychain.supplychain.repository;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.supplychain.supplychain.entity.Item;
import com.supplychain.supplychain.view.ItemListView;

public interface ItemRepository extends Repository<Item, Integer>  {
    
    Item findByItemIdAndStatus(Integer itemId, Byte Status );

    Collection<ItemListView>findAllByUserUserIdAndStatus(Integer userId,Byte status);
   
    Collection<ItemListView> findAllByStatus(Byte status);

    Collection<Item> findAll();

    
    @Query(value = "SELECT * from post_order where pre_order_id in (SELECT pre_order_id FROM pre_order WHERE item_id in(SELECT item_id FROM item WHERE d_person_id=15))",nativeQuery = true)
    Collection<Item>findByPost();

    Collection<Item> findAllByUserUserId(Integer userId);

    Optional<Item> findByItemIdAndUserUserId(Integer itemId, Integer userId);

    // Optional<Item> findByItemIdAndUserUserId(Integer itemId, Integer userId);

    Item save(Item item);

    Page<Item> findAll(Pageable paging);
    // Optional<Item> findByItemId(Integer itemId);

    void delete(Item item);

     Item findByItemId(Integer itemId);

    Collection<Item> findByItemNameStartsWith(String itemName);

    Collection<Item> findAllByOrderByItemNameAsc();

    Collection<Item> findAllByOrderByItemNameDesc();

    Collection<Item> findAllByOrderByCreateDateDesc();

    Collection<Item> findAllByOrderByCreateDateAsc();

    Collection<Item> findByItemNameStartsWithOrderByItemNameAsc(String itemName);

    Collection<Item> findByItemNameStartsWithOrderByItemNameDesc(String itemName);

    Collection<Item> findByItemNameStartsWithOrderByCreateDateDesc(String itemName);

    Collection<Item> findByItemNameStartsWithOrderByCreateDateAsc(String itemName);

    // Item saveAll(List<Item> item);
    // List <Item> findAllCsv();
 

}
