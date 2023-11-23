package com.supplychain.supplychain.repository;
import java.util.Collection;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
// import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import com.supplychain.supplychain.entity.PostOrder;
import com.supplychain.supplychain.entity.PreOrder;


@Transactional
public interface PostOrderRepository extends Repository<PostOrder, Integer>  {

// @Query(value="select * from post_order where pre_order_id in(select pre_order_id from pre_order where supplier_id=?);",nativeQuery = true)
    Collection<PostOrder> findAll();

@Query(value="select * from post_order where pre_order_id in(select pre_order_id from pre_order where supplier_id=(SELECT supplier_id from supplier where user_id=?));",nativeQuery = true)
    Collection<PostOrder>findListById(Integer userId);

    @Modifying
    @Query(value="update post_order set order_status=3 where pre_order_id in(select  pre_order_id from pre_order where d_person_id=15)",nativeQuery = true)
    void findAllShipped();

    @Modifying
    @Query(value="update post_order set order_status=4 where pre_order_id in(select  pre_order_id from pre_order where d_person_id=15)",nativeQuery = true)
    void findAllDelivered();

@Query(value="SELECT * from post_order where pre_order_id in (SELECT pre_order_id FROM pre_order WHERE d_Date is not null)",nativeQuery = true)
    Collection<PostOrder> findAllDate();

    Optional<PostOrder> findById(Integer postorderId);
    
    PostOrder findByPostOrderId(Integer postorderId);

    PostOrder save(PostOrder postorder);

    // @Query(value="update post_order set order_status=3 where pre_order_id in(select  pre_order_id from pre_order where d_person_id=15)",nativeQuery = true)
    // PostOrder bulkShip();
    @Modifying 
    @Transactional  
    @Query(value="update post_order set order_status=3 where order_status=2",nativeQuery = true)
    void bulkShip();

    @Modifying
    @Transactional
    @Query(value="update post_order set order_status=4 where order_status=3",nativeQuery = true)
    void bulkDelivery();

    void delete(PostOrder postorder);

    PostOrder findByPostOrderIdAndStatus(Integer postorderId, byte value);

    

    @Modifying
    @Query(value="DELETE FROM post_order where pre_order_id in(SELECT pre_order_id  from pre_order where user_id=?1 and item_id=?2) and  order_status=0;",nativeQuery = true)
//    @Query(value="Update  post_order  SET status=0 where pre_order_id in(SELECT pre_order_id  from pre_order where user_id=?1 and item_id=?2) and  order_status=0;",nativeQuery = true)
    void acceptOne(Integer userId,Integer itemId);


    

    Collection<PostOrder> findAllByPreOrder(PreOrder preOrder);

    // Optional<PostOrder> findByPostOrderIdAndUserUserId(Integer postorderId, Integer currentUserId);
    PostOrder findByPostOrderIdAndOrderStatus(Integer postorderId,byte orderStatus );


    @Query(value="SELECT * FROM post_order where pre_order_id in (SELECT pre_order_id from pre_order where user_id=?1 and item_id=?2)",nativeQuery = true)
    Collection <PostOrder> getStatus(Integer userId,Integer itemId);

    @Query(value ="SELECT * FROM post_order where pre_order_id in (SELECT pre_order_id from pre_order where item_id=?1 and user_id=?2)",nativeQuery = true)
    Collection<PostOrder>postOrders(Integer itemId,Integer userId);


    Page<PostOrder> findAll(Pageable paging);


    // @Query(value="SELECT * from post_order where pre_order_id in (SELECT pre_order_id FROM pre_order WHERE d_Date is not null)",nativeQuery = true)
    // Page<PostOrder> findAllPaginated(Pageable paging);

}
