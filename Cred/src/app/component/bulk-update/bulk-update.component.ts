import { Component, OnInit } from '@angular/core';

import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { IDropdownSettings } from 'ng-multiselect-dropdown';
import { AcceptDetails } from 'src/app/classes/accept-details';
import { PostOrder } from 'src/app/classes/post-order';
import { AcceptService } from 'src/app/services/accept.service';
import { ItemsService } from 'src/app/services/items.service';

@Component({
  selector: 'app-bulk-update',
  templateUrl: './bulk-update.component.html',
  styleUrls: ['./bulk-update.component.css']
})
export class BulkUpdateComponent implements OnInit {

  itemList:any[]=[];
  itemLists:any[]=[];
  data:any;
  acceptrequest:any;
  private postOrder =new  PostOrder;
  private acceptDetails =new  AcceptDetails;
  id:any;
  i:number=0;
ema:any;
  dropdownList = [
    { item_id: 1, item_text: 'Item1' },
    { item_id: 2, item_text: 'Item2' },
    { item_id: 3, item_text: 'Item3' },
    { item_id: 4, item_text: 'Item4' },
    { item_id: 5, item_text: 'Item5' }
  ];

  dropdownSettings: IDropdownSettings = {
    idField: 'itemId',
    textField: 'itemName',
  };

  constructor(private itemServices:ItemsService,private acceptService:AcceptService,
     private toast:NgToastService,private router:Router) { }

  ngOnInit(): void {

    this.ema=localStorage.getItem('email');

    this.itemServices.getItemDetails().subscribe(
      response =>
      {
        // this.itemList=response
        // console.log(this.itemList);
        
  
      }
  
    );

    this.acceptService.getPostDetails().subscribe(
      response =>
      {
        this.data=response
        console.log(this.data);
        
       
        for(this.i=0;this.i<this.data.length;this.i++){
          this.itemList.push(this.data[this.i].preOrder.item);
         
          
        }
      }
  
    );

  }
logout(){
  localStorage.clear();
  this.router.navigateByUrl('login')
}

  form = new FormGroup({
   
    price:new FormControl(),
    item:new FormControl(),

  
  });



  // this.acceptDetails.itemId = this.itemServices.getId();
  // console.log(this.form.value.supplier)
  // this.acceptDetails.quantity = this.form.value.quantity;
  // this.acceptDetails.address = this.form.value.address;
  // this.acceptDetails.userId = this.user.userId;
  
  // this.form.value.supplier.forEach((element: any) => {
  // this.acceptDetails.supplierId = element.supplierId;
  //   let obj={
  //     "address":this.acceptDetails.address,
  //     "quantity":this.acceptDetails.quantity,
  //     "itemId":this.acceptDetails.itemId,
  //     "supplierId":this.acceptDetails.supplier,
  //     "userId":this.acceptDetails.userId
  //   }






  onsubmit(form: FormGroup) {

console.log("000000000",this.data[0].orderStatus);


    if(this.data[0].orderStatus==2){
  // this.acceptDetails.itemId = this.itemServices.getId();
  // console.log(this.form.value.supplier)
//    this.acceptDetails.price = this.form.value.price;
//  this.acceptDetails.itemId = this.form.value.item.itemId;
  // this.acceptDetails.userId = this.user.userId;

    this.postOrder.price = this.form.value.price;



    // let obj={
      
     
    //   "itemId":this.acceptDetails.itemId,
    //   "itemName":this.acceptDetails.itemName
    // }

    this.postOrder.postOrderId = this.acceptService.getIdz();
    console.log("222222",this.acceptService.getIdz());
    // this.form.value.item.forEach((element: any) => {
    
    // this.acceptDetails.itemId = element.itemId;
    this.acceptService.getBulkShip().subscribe(
    
    
      response=>{
        this.acceptrequest=response;
        console.log("buuuuuuuuuullkkyyy");
        
        this.toast.success({detail:"success message",summary:"accepted",duration:5000})
         //window.location.reload();
      }
      );  
     
  }
    
 


  if(this.data[0].orderStatus==3){
      console.log("aaaaaaaaaaaaannnnnnnnnuuuuuu");
      
      console.log(this.form.value.price)
      this.postOrder.price = this.form.value.price;
      
  
    
      
      this.postOrder.postOrderId = this.acceptService.getIdz();
      console.log("222222",this.acceptService.getIdz());
      this.acceptService.getBulkDeli().subscribe(
      
      
        response=>{
          this.acceptrequest=response;
          this.toast.error({detail:"success message",summary:"accepted",duration:5000})
        }
        );  
    }

      }
  




}