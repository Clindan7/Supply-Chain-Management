package com.supplychain.supplychain.service;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.supplychain.supplychain.entity.Supplier;
import com.supplychain.supplychain.exception.NotFoundException;

import com.supplychain.supplychain.form.SupplierForm;

import com.supplychain.supplychain.view.SupplierView;


public interface SupplierService {
    

    Collection<Supplier> list();

SupplierView currentSupplier();

    SupplierView add(SupplierForm form);

    // SupplierView get(Integer supplierId) throws NotFoundException;

    SupplierView update(Integer supplierId, SupplierForm form) throws NotFoundException;

    SupplierView updateLoggedUser(SupplierForm form);

    void delete(Integer supplierId) throws NotFoundException;

    SupplierView get(Integer supplierId) throws NotFoundException;


}
