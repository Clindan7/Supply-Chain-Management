package com.supplychain.supplychain.repository;
import java.util.Collection;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.supplychain.supplychain.entity.Item;
import com.supplychain.supplychain.entity.PreOrder;
import com.supplychain.supplychain.entity.Supplier;


public interface PreOrderRepository extends Repository<PreOrder, Integer>  {
Collection<PreOrder> findAll();

    Optional<PreOrder> findById(Integer preorderId);
    
    PreOrder findByPreOrderId(Integer preorderId);

    PreOrder save(PreOrder preorder);

    void delete(PreOrder preorder);

    //  PreOrder findByPostOrder(PostOrder postorder);

    Optional<PreOrder> findByPreOrderIdAndUserUserId(Integer preorderId, Integer currentUserId);

    PreOrder findByPreOrderIdAndStatus(Integer preOrderId, byte value);

    Collection<PreOrder> findAllBySupplier(Supplier supplierId);

    Collection<PreOrder> findAllByItemAndUserUserId(Item item, Integer currentUserId);

    @Modifying
    @Query(value="UPDATE pre_order SET status=0 where pre_order_id!=?1 and item_id=?2",nativeQuery = true)
    void deletepreorder(Integer preorderId,Integer itemId);

    // Collection<PreOrder> findAllByItemItemId(Integer itemId);
    Page<PreOrder> findAllBySupplier(Pageable paging,Supplier supplierId);

  

 



   

}
