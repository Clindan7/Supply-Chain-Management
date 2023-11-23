package com.supplychain.supplychain.service;

import java.util.Collection;

import com.supplychain.supplychain.entity.DeliveryPerson;
import com.supplychain.supplychain.form.DeliveryForm;

import com.supplychain.supplychain.exception.NotFoundException;

import com.supplychain.supplychain.view.DeliveryPersonView;
public interface DeliveryPersonService {
    Collection<DeliveryPerson> list();

    DeliveryPersonView add(DeliveryForm form);

    // SupplierView get(Integer supplierId) throws NotFoundException;

    DeliveryPersonView update(Integer dPersonId, DeliveryForm form) throws NotFoundException;

    DeliveryPersonView updateLoggedUser(DeliveryForm form);

    void delete(Integer dPersonId) throws NotFoundException;

    DeliveryPersonView get(Integer dPersonId) throws NotFoundException; 
}
