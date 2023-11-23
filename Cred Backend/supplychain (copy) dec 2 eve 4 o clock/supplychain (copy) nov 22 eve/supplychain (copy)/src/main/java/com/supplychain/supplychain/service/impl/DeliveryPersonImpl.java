package com.supplychain.supplychain.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.supplychain.supplychain.entity.DeliveryPerson;
import com.supplychain.supplychain.exception.NotFoundException;
import com.supplychain.supplychain.form.DeliveryForm;
import com.supplychain.supplychain.repository.DeliveryPersonRepository;
import com.supplychain.supplychain.security.util.SecurityUtil;
import com.supplychain.supplychain.service.DeliveryPersonService;
import com.supplychain.supplychain.view.DeliveryPersonView;
import org.springframework.transaction.annotation.Transactional;


@Service
public class DeliveryPersonImpl implements DeliveryPersonService {
    @Autowired
    private DeliveryPersonRepository deliveryPersonRepository;

    @Override
    public Collection<DeliveryPerson> list() {
        return deliveryPersonRepository.findAll();
    }

    @Override
    public DeliveryPersonView add(DeliveryForm form) {
        System.out.println("user id : "+SecurityUtil.getCurrentUserId());
        return new DeliveryPersonView(deliveryPersonRepository.save(new DeliveryPerson(form)));
    }


    @Override
    @Transactional
    public DeliveryPersonView update(Integer dPersonId, DeliveryForm form) throws NotFoundException {
        return deliveryPersonRepository.findById(dPersonId)
        .map((deliveryPerson) -> {
            return new DeliveryPersonView(deliveryPersonRepository.save(deliveryPerson.update(form)));
        }).orElseThrow(NotFoundException::new);
}


@Override
@Transactional
public DeliveryPersonView updateLoggedUser(DeliveryForm form){
    
    DeliveryPerson deliveryPerson=deliveryPersonRepository.findBydPersonId(SecurityUtil.getCurrentUserId());
            
                return new DeliveryPersonView(deliveryPersonRepository.save(deliveryPerson.update(form)));
            
}

@Override
@Transactional
public void delete(Integer supplierId) throws NotFoundException {



    deliveryPersonRepository.delete(
        deliveryPersonRepository.findById(supplierId)
                    .orElseThrow(NotFoundException::new)
    );
}



    @Override
    public DeliveryPersonView get(Integer dPersonId) throws NotFoundException {
        
        return new DeliveryPersonView(deliveryPersonRepository.findBydPersonId(dPersonId));
    }
}
