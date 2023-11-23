package com.supplychain.supplychain.service.impl;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.supplychain.supplychain.entity.DeliveryPerson;
import com.supplychain.supplychain.entity.Item;
import com.supplychain.supplychain.entity.PreOrder;
import com.supplychain.supplychain.entity.Supplier;
import com.supplychain.supplychain.entity.User;
import com.supplychain.supplychain.exception.NotFoundException;
import com.supplychain.supplychain.form.PreOrderArray;
import com.supplychain.supplychain.form.PreOrderForm;
import com.supplychain.supplychain.repository.DeliveryPersonRepository;
import com.supplychain.supplychain.repository.ItemRepository;
import com.supplychain.supplychain.repository.PreOrderRepository;
import com.supplychain.supplychain.repository.SupplierRepository;
import com.supplychain.supplychain.repository.UserRepository;
import com.supplychain.supplychain.security.util.SecurityUtil;
import com.supplychain.supplychain.service.EmailService;
import com.supplychain.supplychain.service.PreOrderService;

import com.supplychain.supplychain.view.PreOrderView;

import ch.qos.logback.core.status.Status;



@Service
public class PreOrderServiceImpl implements PreOrderService {

    @Autowired
    private PreOrderRepository preorderRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DeliveryPersonRepository deliveryPersonRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Collection<PreOrder> list() {

        User user=userRepository.findByUserId(SecurityUtil.getCurrentUserId());
    
        Supplier supplierId=supplierRepository.findByUser(user);
        return preorderRepository.findAllBySupplier(supplierId);
    }
    
    @Override
    public List<PreOrder> findPaginated(int pageNo, int pageSize) {
        User user=userRepository.findByUserId(SecurityUtil.getCurrentUserId());
    
        Supplier supplierId=supplierRepository.findByUser(user);
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<PreOrder> pagedResult = preorderRepository.findAllBySupplier(paging,supplierId);

        return pagedResult.toList();
    }


    @Override
    public PreOrderView add(PreOrderForm form) {
        System.out.println("user id : "+SecurityUtil.getCurrentUserId());

        // User user=userRepository.findByUserId(SecurityUtil.getCurrentUserId());
    
        Supplier supplierId=supplierRepository.findBySupplierId(form.getSupplierId());
        User id= supplierId.getUser();
        String mail= id.getEmail();
        String content="Request sent";
        String subject="Subject";
        emailService.sendEmail(subject, content, mail);
        DeliveryPerson deliveryPerson=deliveryPersonRepository.findBydPersonId(form.getdPersonId());
  
       
        return new PreOrderView(preorderRepository.save(new PreOrder(form,deliveryPerson, SecurityUtil.getCurrentUserId())));
    }

    @Override
    public PreOrderView update(Integer preorderId, PreOrderForm form) throws NotFoundException {
        DeliveryPerson deliveryPerson=deliveryPersonRepository.findBydPersonId(form.getdPersonId());    
        return preorderRepository.findById(preorderId)
            .map((preorder) -> {

                return new PreOrderView(preorderRepository.save(preorder.update(form,deliveryPerson)));
            }).orElseThrow(NotFoundException::new);
    }

    @Override
    @Transactional
    public PreOrderView updateLoggedUser(PreOrderForm form){
        
        PreOrder preorder=preorderRepository.findByPreOrderId(SecurityUtil.getCurrentUserId());
                
                    return new PreOrderView(preorderRepository.save(preorder.update(form)));
                
    }
    
    @Override
    @Transactional
    public void delete(Integer preOrderId) throws NotFoundException {
   
        PreOrder preOrder=preorderRepository.findByPreOrderIdAndStatus(preOrderId,PreOrder.Status.ACTIVE.value);
        
        User userId= preOrder.getUser();
        String to=userId.getEmail(); 
        String content="This is the content";
        String message="REJECTED";
        emailService.sendEmail(content, message, to);

        
        preOrder.setStatus(Item.Status.INACTIVE.value);
        preorderRepository.save(preOrder);
        
     
                
    }
    
    
       
    
        @Override
        public PreOrderView get(Integer preorderId) throws NotFoundException {
            
            return new PreOrderView(preorderRepository.findByPreOrderId(preorderId));
        }
     

        @Override
        public Collection<PreOrder> getItems(Integer itemId) throws NotFoundException {
        
            Item item=itemRepository.findByItemId(itemId);
          
        return preorderRepository.findAllByItemAndUserUserId(item,SecurityUtil.getCurrentUserId());
            // return preorderRepository.findAllByItemItemId(itemId);
    }

    @Override
    public Collection<PreOrderView> addSupplier( PreOrderForm form) {
        // supplierRepository.save(new Supplier())
         preorderRepository.save(new PreOrder(form,SecurityUtil.getCurrentUserId()));
      return null;
    }
  

    @Override
    public Collection<PreOrderView> addItem( PreOrderForm form) {
        // supplierRepository.save(new Supplier())
         preorderRepository.save(new PreOrder(form,SecurityUtil.getCurrentUserId()));
      return null;
    }

    @Override
    public void addArray(PreOrderArray form){
        Integer j=0;
         PreOrderForm preOrderForm=new PreOrderForm();
         preOrderForm.setAddress(form.getAddress());
         preOrderForm.setSupplierId(form.getSupplierId());
         for(Integer i:form.getItemId()){
            preOrderForm.setItemId(i);
            List<Integer> ar=form.getQuantity();
            preOrderForm.setQuantity(ar.get(j));
            j++;
            add(preOrderForm);
         }
       
        
    }
       


    // @Override
    // @Transactional
    // @Scheduled(cron="0 0 */3 * * *")
    // public void deleteSheduler() {
    //     Date dt = new Date();
    //     Collection<PreOrder> preOrder = preorderRepository.findAll();
    //     for(PreOrder pre:preOrder){
    //         if(pre.getCreateDate().compareTo(dt) <= 3) {
    //             pre.setStatus(PreOrder.Status.INACTIVE.value);
    //             preorderRepository.save(pre);
    //         }
    //     }
    // }
   

}
