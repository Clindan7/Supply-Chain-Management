package  com.supplychain.supplychain.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.supplychain.supplychain.entity.PreOrder;
import com.supplychain.supplychain.entity.PostOrder;
import  com.supplychain.supplychain.entity.Supplier;

import com.supplychain.supplychain.form.PostOrderForm;
import  com.supplychain.supplychain.form.SupplierForm;
import com.supplychain.supplychain.security.util.SecurityUtil;
import com.supplychain.supplychain.service.ItemService;
import com.supplychain.supplychain.service.PostOrderService;
import  com.supplychain.supplychain.service.SupplierService;

import com.supplychain.supplychain.view.PostOrderView;
import  com.supplychain.supplychain.view.SupplierView;

@RestController
@RequestMapping("/postorder")
public class PostOrderController {

    @Autowired
    private PostOrderService postorderService;

    @Autowired
    private ItemService itemService;


    @PostMapping
    public PostOrderView add(@Valid @RequestBody PostOrderForm form) {
        return postorderService.add(form);
    }


    
    @GetMapping("/bulkship/orders")
    public void bulkShip() {
         postorderService.bulkShip();
    }

    @GetMapping("/bulkdelivery/orders")
    public void bulkDelivery() {
         postorderService.bulkDelivery();
    }


    @GetMapping
    public Collection<PostOrder> list() {
        return postorderService.list();
    }


    @GetMapping("/postorderbyid")
    public Collection<PostOrder> listById() {
        return postorderService.listById();
    }



        
    @GetMapping("/listbydate")
    public Collection<PostOrder> listbydate() {
        return postorderService.listByDate();
    }
    


 

    @GetMapping("/{postorderId}")
    public PostOrderView get(@PathVariable("postorderId") Integer postorderId) {
        
        return postorderService.get(postorderId);
    }

    @GetMapping("/getpostorder/{postorderId}")
    public Collection<PostOrder> getOrders(@PathVariable("postorderId") Integer itemId) {
        return postorderService.getOrders(itemId);
    }

    @GetMapping("/getpostorderbyquery/{itemId}")
    public Collection<PostOrder> getOrdersByQuery(@PathVariable("itemId") Integer itemId) {
        return postorderService.getOrdersByQuery(itemId);
    }
    
    @GetMapping("/status/{itemId}")
    public Collection<PostOrder>getStatus(@PathVariable("itemId") Integer itemId){
    return postorderService.getStatus(itemId);
    }



    @PutMapping("/{postorderId}")
    public PostOrderView update(
            @PathVariable("postorderId") Integer postorderId,
            @Valid @RequestBody PostOrderForm form
    ) {
        return postorderService.update(postorderId, form);
    }

    @DeleteMapping("/deleterequest/{postorderId}")
    public void delete(@PathVariable("postorderId") Integer postorderId) {
        postorderService.delete(postorderId);
    }




    @PutMapping
    public PostOrderView updateLoggedUser(
            @Valid @RequestBody PostOrderForm form
    ) {
        return postorderService.updateLoggedUser(form);
    }


    @GetMapping("/listallpostorder/{pageNo}/{pageSize}")
    public List<PostOrder> getPaginatedPostorder(@PathVariable int pageNo, 
            @PathVariable int pageSize) {

        return postorderService.findPaginated(pageNo, pageSize);
    }


    // @GetMapping("/listbydatepage/{pageNo}/{pageSize}")
    // public List<PostOrder> getPaginatedDelivery(@PathVariable int pageNo, 
    //         @PathVariable int pageSize) {

    //     return postorderService.findPaginatedDelivery(pageNo, pageSize);
    // }







@PostMapping("/bulkshipped/{orders}")
public void changeStatus(@PathVariable("orders") List<Integer> orders
){
    
        postorderService.changeStatus(orders);
}


}
