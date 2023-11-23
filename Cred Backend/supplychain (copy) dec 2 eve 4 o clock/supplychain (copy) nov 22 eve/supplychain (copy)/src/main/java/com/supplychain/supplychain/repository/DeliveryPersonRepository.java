package com.supplychain.supplychain.repository;
import java.util.Collection;
import java.util.Optional;

import org.springframework.data.repository.Repository;

import com.supplychain.supplychain.entity.DeliveryPerson;
import com.supplychain.supplychain.entity.User;
public interface DeliveryPersonRepository extends Repository<DeliveryPerson, Integer> {
    Collection<DeliveryPerson> findAll();

    Optional<DeliveryPerson> findById(Integer dPersonId);
    
    
    DeliveryPerson findBydPersonId(Integer dPersonId);

    DeliveryPerson save(DeliveryPerson deliveryPerson);

    void delete(DeliveryPerson deliveryPerson);

    // Optional<DeliveryPerson> findBySupplierIdAndUserUserId(Integer supplierId, Integer currentUserId);

    DeliveryPerson findByUser(User currentUserId);
}
