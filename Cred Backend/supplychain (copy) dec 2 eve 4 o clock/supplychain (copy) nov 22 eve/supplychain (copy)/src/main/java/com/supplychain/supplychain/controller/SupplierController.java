package  com.supplychain.supplychain.controller;

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

import  com.supplychain.supplychain.entity.Supplier;
import  com.supplychain.supplychain.form.SupplierForm;

import  com.supplychain.supplychain.service.SupplierService;
import com.supplychain.supplychain.service.UserService;
import  com.supplychain.supplychain.view.SupplierView;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;


    @Autowired
    private UserService userService;

    @PostMapping
    public SupplierView add(@Valid @RequestBody SupplierForm form) {
        return supplierService.add(form);
    }


    
    @GetMapping
    public Collection<Supplier> list() {
        return supplierService.list();
    }


    @GetMapping("/currentsup")
    public SupplierView currentSupplier() {
        return supplierService.currentSupplier();
    }




    @GetMapping("/{supplierId}")
    public SupplierView get(@PathVariable("supplierId") Integer supplierId) {
        return supplierService.get(supplierId);
    }


    @PutMapping("/{supplierId}")
    public SupplierView update(
            @PathVariable("supplierId") Integer supplierId,
            @Valid @RequestBody SupplierForm form
    ) {
        return supplierService.update(supplierId, form);
    }

    @DeleteMapping("/{supplierId}")
    public void delete(@PathVariable("supplierId") Integer supplierId) {
        supplierService.delete(supplierId);
    }




    @PutMapping
    public SupplierView updateLoggedUser(
            @Valid @RequestBody SupplierForm form
    ) {
        return supplierService.updateLoggedUser(form);
    }

}
