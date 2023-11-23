
package com.supplychain.supplychain.controller;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.supplychain.supplychain.entity.DeliveryPerson;
import com.supplychain.supplychain.form.DeliveryForm;
import com.supplychain.supplychain.service.DeliveryPersonService;
import com.supplychain.supplychain.view.DeliveryPersonView;

@RestController
@RequestMapping("/delivery")
public class DeliveryPersonController {
    

    @Autowired
    private DeliveryPersonService deliveryPersonService;


    @PostMapping
    public DeliveryPersonView add(@Valid @RequestBody DeliveryForm form) {
        return deliveryPersonService.add(form);
    }


    
    @GetMapping
    public Collection<DeliveryPerson> list() {
        return deliveryPersonService.list();
    }

    @GetMapping("/{dPersonId}")
    public DeliveryPersonView get(@PathVariable("dPersonId") Integer dPersonId) {
        return deliveryPersonService.get(dPersonId);
    }


    @PutMapping("/{dPersonId}")
    public DeliveryPersonView update(
            @PathVariable("dPersonId") Integer dPersonId,
            @Valid @RequestBody DeliveryForm form
    ) {
        return deliveryPersonService.update(dPersonId, form);
    }

    @DeleteMapping("/{dPersonId}")
    public void delete(@PathVariable("dPersonId") Integer dPersonId) {
        deliveryPersonService.delete(dPersonId);
    }




}
