package com.supplychain.supplychain.repository;
import java.util.Collection;
import java.util.Optional;

import org.springframework.data.repository.Repository;

import com.supplychain.supplychain.entity.Supplier;
import com.supplychain.supplychain.entity.User;


public interface SupplierRepository extends Repository<Supplier, Integer>  {

    Collection<Supplier> findAll();

    Optional<Supplier> findById(Integer supplierId);
    
    Supplier findBySupplierId(Integer supplierId);



    Supplier save(Supplier supplier);

    void delete(Supplier supplier);

    Optional<Supplier> findBySupplierIdAndUserUserId(Integer supplierId, Integer currentUserId);

    Supplier findByUser(User currentUserId);
   
}
