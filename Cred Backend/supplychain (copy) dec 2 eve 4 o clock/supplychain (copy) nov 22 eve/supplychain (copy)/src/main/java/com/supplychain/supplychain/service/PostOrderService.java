package com.supplychain.supplychain.service;
import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.supplychain.supplychain.entity.PreOrder;
import com.supplychain.supplychain.entity.PostOrder;
import com.supplychain.supplychain.exception.NotFoundException;


import com.supplychain.supplychain.form.PostOrderForm;

import com.supplychain.supplychain.view.PostOrderView;



public interface PostOrderService {
    
    public Collection<PostOrder> listByDate();
    

    void changeStatus(List<Integer> orders);

    Collection<PostOrder> list();

    Collection<PostOrder>listById();

    PostOrderView add(PostOrderForm form);

    // PostOrderView BulkShip(Integer postorderid, PostOrderForm form);
    void bulkShip();

    void bulkDelivery();

    // SupplierView get(Integer supplierId) throws NotFoundException;

    PostOrderView update(Integer postorderId, PostOrderForm form) throws NotFoundException;

    PostOrderView updateLoggedUser(PostOrderForm form);

    void delete(Integer postorderId) throws NotFoundException;

    PostOrderView get(Integer postorderId) throws NotFoundException;

    // Collection<PostOrder> listbyitempost();

    Collection<PostOrder> getOrders(Integer itemId) throws NotFoundException;


 void orderStatusUpdate(Integer postorderId, Integer itemId) throws NotFoundException ;


 void orderStatusUpdate1(Integer postorderId) throws NotFoundException ;
void  orderStatusShipped(Integer postorderId) throws NotFoundException;
void orderStatusDelivered(Integer postorderId) throws NotFoundException;

Collection<PostOrder> getStatus(Integer itemId);

Collection<PostOrder> getOrdersByQuery(Integer itemId);


List<PostOrder> findPaginated(int pageNo, int pageSize);
// List<PostOrder> findPaginatedDelivery(int pageNo, int pageSize);

}