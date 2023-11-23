package com.supplychain.supplychain.service.impl;

import java.util.Collection;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.supplychain.supplychain.entity.PreOrder;
import com.supplychain.supplychain.entity.Supplier;
import com.supplychain.supplychain.entity.User;
import com.supplychain.supplychain.entity.PostOrder.OrderStatus;
import com.supplychain.supplychain.entity.Supplier.Role;
import com.supplychain.supplychain.entity.Item;
import com.supplychain.supplychain.entity.PostOrder;
import com.supplychain.supplychain.exception.NotFoundException;

import com.supplychain.supplychain.form.PostOrderForm;

import com.supplychain.supplychain.repository.PostOrderRepository;
import com.supplychain.supplychain.repository.PreOrderRepository;
import com.supplychain.supplychain.repository.SupplierRepository;
import com.supplychain.supplychain.repository.UserRepository;
import com.supplychain.supplychain.security.util.SecurityUtil;
import com.supplychain.supplychain.service.EmailService;
import com.supplychain.supplychain.service.PostOrderService;

import com.supplychain.supplychain.view.PostOrderView;



@Service
public class PostOrderServiceImpl implements PostOrderService {

    @Autowired
    private PostOrderRepository postorderRepository;


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PreOrderRepository preOrderRepository;
@Autowired
private EmailService emailService;



    @Override
    public Collection<PostOrder> list() {
        return postorderRepository.findAll();
    }

@Override
    public Collection<PostOrder>listById(){
        return postorderRepository.findListById(SecurityUtil.getCurrentUserId());
    }

@Override
public void changeStatus(List<Integer> orders){
    for(Integer i:orders){


        PostOrder p= postorderRepository.findByPostOrderId(i);
if(p.getOrderStatus()==4)
continue;

        p.setOrderStatus((byte)(p.getOrderStatus()+1));

        postorderRepository.save(p);
        System.out.println("here"+p.getPostOrderId());

    }

}
    





    @Override
    public PostOrderView add(PostOrderForm form) {
   PreOrder preorderId= preOrderRepository.findByPreOrderId(form.getPreOrderId());
User userId= preorderId.getUser();
String to=userId.getEmail(); 
System.out.println("aaaaaaaaaaaa"+to);
String content="sadas";
String message="sdaa";
emailService.sendEmail(content, message, to);

        return new PostOrderView(postorderRepository.save(new PostOrder(form, SecurityUtil.getCurrentUserId())));
    }


    @Override
    @Transactional
    public PostOrderView update(Integer postorderId, PostOrderForm form) throws NotFoundException {
        return postorderRepository.findById(postorderId)
        .map((postorder) -> {
            return new PostOrderView(postorderRepository.save(postorder.update(form)));
        }).orElseThrow(NotFoundException::new);
}



// @Override
// @Transactional
// public PostOrderView BulkShip(Integer postorderId, PostOrderForm form) throws NotFoundException {
//     return postorderRepository.findById(postorderId)
//     .map((postorder) -> {
//         return new PostOrderView(postorderRepository.save(postorder.BulkShip(form)));
//     }).orElseThrow(NotFoundException::new);
// }


@Override
public void bulkShip() {
  
     postorderRepository.bulkShip();
}

@Override
public void bulkDelivery() {
  
     postorderRepository.bulkDelivery();
}


@Override
@Transactional
public PostOrderView updateLoggedUser(PostOrderForm form){
    
    PostOrder postorder=postorderRepository.findByPostOrderId(SecurityUtil.getCurrentUserId());
            
                return new PostOrderView(postorderRepository.save(postorder.update(form)));
            
}

@Override
@Transactional
public void delete(Integer postorderId) throws NotFoundException {
    // postorderRepository.delete(
    //     postorderRepository.findById(postorderId)
    //                 .orElseThrow(NotFoundException::new)
    // );
    PostOrder postOrder =postorderRepository.findByPostOrderIdAndStatus(postorderId,PostOrder.Status.ACTIVE.value);
    postOrder.setStatus(Item.Status.INACTIVE.value);
    postorderRepository.save(postOrder);
}


    @Override
    public PostOrderView get(Integer postorderId) throws NotFoundException {
        
        return new PostOrderView(postorderRepository.findByPostOrderId(postorderId));
    }

    @Override
    public Collection<PostOrder> getOrders(Integer preOrderId) throws NotFoundException {
        
        PreOrder preOrder=preOrderRepository.findByPreOrderId(preOrderId);
        return postorderRepository.findAllByPreOrder(preOrder);
    }


    @Override
    @Transactional
    public void orderStatusUpdate(Integer postorderId,Integer itemId) throws NotFoundException {

  

        PostOrder postorder=postorderRepository.findByPostOrderIdAndOrderStatus(postorderId,PostOrder.OrderStatus.REQUESTED.value);
        
        //preOrderRepository.deletepreorder(postorderId,itemId);
        postorder.setOrderStatus(PostOrder.OrderStatus.ACCEPTED.value);
        postorderRepository.acceptOne(SecurityUtil.getCurrentUserId(),itemId);
      

System.out.println("/////////////"+postorder.getPreOrder().getPreOrderId());
System.out.println("....................."+itemId);
        postorderRepository.save(postorder);
        // postorder.delete(PostOrder.OrderStatus.REQUESTED.value);

        return;
                
    }

    @Override
    @Transactional
     public void  orderStatusShipped(Integer postorderId) throws NotFoundException{

        

        PostOrder postorder=postorderRepository.findByPostOrderIdAndOrderStatus(postorderId,PostOrder.OrderStatus.DELIVERYPERSON.value);
        

        postorder.setOrderStatus(PostOrder.OrderStatus.SHIPPED.value);
       
   

        postorderRepository.save(postorder);
        return;
                
    
     }

     @Override
     @Transactional

     public void orderStatusDelivered(Integer postorderId) throws NotFoundException{

        PostOrder postorder=postorderRepository.findByPostOrderIdAndOrderStatus(postorderId,PostOrder.OrderStatus.SHIPPED.value);
        
        postorder.setOrderStatus(PostOrder.OrderStatus.DELIVERED.value);
       
   

        postorderRepository.save(postorder);
        return;


     }

    @Override
    @Transactional
    public void orderStatusUpdate1(Integer postorderId) throws NotFoundException {

        PostOrder postorder=postorderRepository.findByPostOrderIdAndOrderStatus(postorderId,PostOrder.OrderStatus.ACCEPTED.value);
        
        postorder.setOrderStatus(PostOrder.OrderStatus.DELIVERYPERSON.value);
       
   

        postorderRepository.save(postorder);
        return;
                
    }

 
    @Override
    @Transactional
public Collection<PostOrder> getStatus(Integer itemId) {

   return postorderRepository.getStatus(SecurityUtil.getCurrentUserId(),itemId);
}

@Override
public Collection<PostOrder> getOrdersByQuery(Integer itemId){
    return postorderRepository.postOrders(itemId, SecurityUtil.getCurrentUserId());
}



@Override
public List<PostOrder> findPaginated(int pageNo, int pageSize) {

    Pageable paging = PageRequest.of(pageNo, pageSize);
    Page<PostOrder> pagedResult = postorderRepository.findAll(paging);

    return pagedResult.toList();
}


// @Override
// public List<PostOrder> findPaginatedDelivery(int pageNo, int pageSize) {

//     Pageable paging = PageRequest.of(pageNo, pageSize);
//     Page<PostOrder> pagedResult = postorderRepository.findAllPaginated(paging);

//     return pagedResult.toList();
// }

@Override
public Collection<PostOrder> listByDate() {
    return postorderRepository.findAllDate();
}



}
