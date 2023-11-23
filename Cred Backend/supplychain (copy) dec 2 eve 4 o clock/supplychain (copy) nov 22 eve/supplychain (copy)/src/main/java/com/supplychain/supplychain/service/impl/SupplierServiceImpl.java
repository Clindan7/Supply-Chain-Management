package com.supplychain.supplychain.service.impl;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.supplychain.supplychain.entity.Supplier;
import com.supplychain.supplychain.exception.NotFoundException;
import com.supplychain.supplychain.form.SupplierForm;
import com.supplychain.supplychain.repository.PreOrderRepository;
import com.supplychain.supplychain.repository.SupplierRepository;
import com.supplychain.supplychain.repository.UserRepository;
import com.supplychain.supplychain.security.util.SecurityUtil;
import com.supplychain.supplychain.service.SupplierService;
import com.supplychain.supplychain.view.SupplierView;


@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Collection<Supplier> list() {
        return supplierRepository.findAll();
    }

    
    @Override
    public SupplierView currentSupplier() {
        return new SupplierView(
                supplierRepository.findByUser(userRepository.findByUserId(SecurityUtil.getCurrentUserId())
        ));
    }



    @Override
    public SupplierView add(SupplierForm form) {
        System.out.println("user id : "+SecurityUtil.getCurrentUserId());
        return new SupplierView(supplierRepository.save(new Supplier(form)));
    }


    @Override
    @Transactional
    public SupplierView update(Integer supplierId, SupplierForm form) throws NotFoundException {
        return supplierRepository.findById(supplierId)
        .map((supplier) -> {
            return new SupplierView(supplierRepository.save(supplier.update(form)));
        }).orElseThrow(NotFoundException::new);
}


@Override
@Transactional
public SupplierView updateLoggedUser(SupplierForm form){
    
    Supplier supplier=supplierRepository.findBySupplierId(SecurityUtil.getCurrentUserId());
            
                return new SupplierView(supplierRepository.save(supplier.update(form)));
            
}

@Override
@Transactional
public void delete(Integer supplierId) throws NotFoundException {



    supplierRepository.delete(
        supplierRepository.findById(supplierId)
                    .orElseThrow(NotFoundException::new)
    );
}

    @Override
    public SupplierView get(Integer supplierId) throws NotFoundException {
        
        return new SupplierView(supplierRepository.findBySupplierId(supplierId));
    }


}
