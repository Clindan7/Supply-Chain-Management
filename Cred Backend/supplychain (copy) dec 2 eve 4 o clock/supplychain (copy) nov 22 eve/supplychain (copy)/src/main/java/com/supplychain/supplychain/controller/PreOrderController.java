package com.supplychain.supplychain.controller;

import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.supplychain.supplychain.entity.Item;
import com.supplychain.supplychain.entity.PreOrder;
import com.supplychain.supplychain.form.PostOrderForm;
import com.supplychain.supplychain.form.PreOrderArray;
import com.supplychain.supplychain.form.PreOrderForm;
import com.supplychain.supplychain.service.PostOrderService;
import com.supplychain.supplychain.service.PreOrderService;
import com.supplychain.supplychain.view.PreOrderView;

@RestController
@RequestMapping("/preorder")
public class PreOrderController {

    @Autowired
    private PreOrderService preorderService;


    @PostMapping
    public PreOrderView add(@Valid @RequestBody PreOrderForm form) {
        System.out.println("###########"+form);
        return preorderService.add(form);

    }


    @PostMapping("/array")
    public void addArray(@Valid @RequestBody PreOrderArray form) {
        System.out.println("###########"+form);

         preorderService.addArray(form);

    }


    @GetMapping
    public Collection<PreOrder> list() {
        return preorderService.list();
    }



    @GetMapping("/listallpreorder/{pageNo}/{pageSize}")
    public List<PreOrder> getPaginatedPreorder(@PathVariable int pageNo, 
            @PathVariable int pageSize) {

        return preorderService.findPaginated(pageNo, pageSize);
    }


  


    @GetMapping("/{preorderId}")
    public PreOrderView get(@PathVariable("preorderId") Integer preorderId) {
        return preorderService.get(preorderId);
    }
    

    @GetMapping("/getitem/{getbyitemId}")
    public Collection<PreOrder> getItems(@PathVariable("getbyitemId") Integer itemId) {
        return preorderService.getItems(itemId);
    }

   

    @PutMapping("/{preorderId}")
    public PreOrderView update(
            @PathVariable("preorderId") Integer preorderId,
            @Valid @RequestBody PreOrderForm form
    ) {
        System.out.println("nnnnnn"+ form.getdPersonId());
        return preorderService.update(preorderId, form);
    }
    

    @DeleteMapping("/{preorderId}")
    public void delete(@PathVariable("preorderId") Integer preorderId) {
        preorderService.delete(preorderId);
    }
       
    @PostMapping("/pre")
    public Collection<PreOrderView> addSupplier(
            @Valid @RequestBody PreOrderForm form) {
        return preorderService.addSupplier(form);

    }

    @PostMapping("/additem")
    public Collection<PreOrderView>addItem(
            @Valid @RequestBody PreOrderForm form) {
        return preorderService.addItem(form);

    }




    @PutMapping
    public PreOrderView updateLoggedUser(
            @Valid @RequestBody PreOrderForm form
    ) {
        return preorderService.updateLoggedUser(form);
    }



}
