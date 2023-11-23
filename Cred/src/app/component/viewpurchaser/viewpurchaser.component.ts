import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';

import { Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { IDropdownSettings } from 'ng-multiselect-dropdown';
import { AcceptDetails } from 'src/app/classes/accept-details';
import { ItemDetails } from 'src/app/classes/item-details';
import { ItemsService } from 'src/app/services/items.service';
import { PreorderService } from 'src/app/services/preorder.service';

import { SupplierService } from 'src/app/services/supplier.service';
import { UserService } from 'src/app/services/user.service';


@Component({
  selector: 'app-viewpurchaser',
  templateUrl: './viewpurchaser.component.html',
  styleUrls: ['./viewpurchaser.component.css']
})
export class ViewpurchaserComponent implements OnInit {
  supplierList:any;
  supplierLists:any[]=[];
  data: any;
 itemId:any;
  user:any;
  checkid: any;
  i:any;
  col:any;
  fruits: ItemDetails[]=[]; 
  dropdownList = [
    { item_id: 1, item_text: 'Item1' },
    { item_id: 2, item_text: 'Item2' },
    { item_id: 3, item_text: 'Item3' },
    { item_id: 4, item_text: 'Item4' },
    { item_id: 5, item_text: 'Item5' }
  ];

  dropdownSettings: IDropdownSettings = {
    idField: 'supplierId',
    textField: 'supplierName',
  };

private acceptDetails =new  AcceptDetails;
  constructor(private supplierServices:SupplierService, 
    private router: Router,private userService:UserService,
     private preOrderService:PreorderService,private toast:NgToastService, 
      private itemServices:ItemsService) { }

  ngOnInit(): void {

    this.itemServices.getItemDetailsbyid(this.itemServices.getId()).subscribe(
      response =>
      {
        
        this.data=response
        console.log(this.data)
       this.checkid= console.log(this.itemServices.getId())
       
      }
    );
    

      
 
  

    console.log(this.supplierServices.getId())
    this.supplierServices.getSupplierDetailsbyid(this.supplierServices.getId()).subscribe(
      response =>
      {
        
        this.supplierList=response
        console.log(this.supplierList)
       this.checkid= console.log(this.supplierServices.getId())
       
      }
    );
    

    this.userService.getCurrentUser().subscribe(
      response =>
      {
        
        this.user=response
        console.log(this.user)
     
     
      }
    );
  
   
      this.supplierServices.getSupplierDetails().subscribe(
        response =>
        {
          this.supplierList=response
          console.log(this.supplierList)
    
        }
    
      );
      
  }

  form = new FormGroup({
    quantity:new FormControl(),
    address: new FormControl(),
    supplier:new FormControl(),

  
  });





  onsubmit(form: FormGroup) {

    console.log(this.form.value.supplier)
    this.acceptDetails.quantity = this.form.value.quantity;
    this.acceptDetails.address = this.form.value.address;

    console.log(this.acceptDetails.supplierId)
    this.acceptDetails.userId = this.user.userId;
    this.acceptDetails.itemId = this.itemServices.getId();
    this.form.value.supplier.forEach((element: any) => {
      // this.supplierLists.push(element.supplierId)
    this.acceptDetails.supplierId = element.supplierId;
      let obj={
        "address":this.acceptDetails.address,
        "quantity":this.acceptDetails.quantity,
        "itemId":this.acceptDetails.itemId,
        "supplierId":this.acceptDetails.supplierId
      }
      this.preOrderService.savePreOrderDetails(obj).subscribe(
        response => {
          this.toast.success({ detail: "success message", summary: "request sent", duration: 5000 })
          if (response) {
            this.toast.success({ detail: "success message", summary: "request sent", duration: 5000 })
  
            this.router.navigateByUrl('/purchaser');
          }
        },
       
      );

    });



       
      }
    }
    



