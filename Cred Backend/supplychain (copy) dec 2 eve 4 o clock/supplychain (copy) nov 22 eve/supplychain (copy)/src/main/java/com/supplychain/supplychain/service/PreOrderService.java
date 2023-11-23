package com.supplychain.supplychain.service;
import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.supplychain.supplychain.entity.PreOrder;
import com.supplychain.supplychain.exception.NotFoundException;
import com.supplychain.supplychain.form.PreOrderArray;
import com.supplychain.supplychain.form.PreOrderForm;

import com.supplychain.supplychain.view.PreOrderView;



public interface PreOrderService {
    
    void addArray(PreOrderArray form);
    Collection<PreOrder> list();

    List<PreOrder> findPaginated(int pageNo, int pageSize);


    PreOrderView add(PreOrderForm form);

    // SupplierView get(Integer supplierId) throws NotFoundException;

    PreOrderView update(Integer preorderId, PreOrderForm form) throws NotFoundException;

    PreOrderView updateLoggedUser(PreOrderForm form);

    void delete(Integer preorderId) throws NotFoundException;

    PreOrderView get(Integer preorderId) throws NotFoundException;

    // PreOrderView getItems(Integer itemId) throws NotFoundException;
    Collection<PreOrder> getItems(Integer itemId);

    Collection<PreOrderView> addSupplier( PreOrderForm form);

    Collection<PreOrderView> addItem( PreOrderForm form);



    // void deleteSheduler();

}
