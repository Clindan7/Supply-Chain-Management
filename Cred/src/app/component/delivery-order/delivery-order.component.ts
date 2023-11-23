import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { PageEvent } from '@angular/material/paginator';
import { Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { IDropdownSettings } from 'ng-multiselect-dropdown';
import { AnonymousSubject } from 'rxjs/internal/Subject';
import { AcceptDetails } from 'src/app/classes/accept-details';
import { AcceptService } from 'src/app/services/accept.service';
import { DeliveryServiceService } from 'src/app/services/delivery-service.service';
import { ItemsService } from 'src/app/services/items.service';
import { PreorderService } from 'src/app/services/preorder.service';
import { DeliveryProfileComponent } from '../delivery-profile/delivery-profile.component';
import { SupplierProfileComponent } from '../supplier-profile/supplier-profile.component';

@Component({
  selector: 'app-delivery-order',
  templateUrl: './delivery-order.component.html',
  styleUrls: ['./delivery-order.component.css']
})
export class DeliveryOrderComponent implements OnInit {
  request: any;
  data: any;
 ema:any;
  acceptrequest: any;
  supplierList:any;
  item:any;
  len:any;
  q:any=0;
  q1:any=1;

  acceptdetails= new AcceptDetails;
  postorder:any;
  // form:FormGroup;
  searchText:any;
  buttonDisabled:boolean=false;
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

  newArray=new Array;
  bulkset: any;
  Paginatedata: any;
  sortedData: any;
  p:any=0;
  p1:any=1;
  test:any;
  deliverydata:any;

  constructor(private acceptService:AcceptService,
    private router:Router, private preOrderService:PreorderService,
    private deliveryS:DeliveryServiceService, private toast:NgToastService
    ,private itemS:ItemsService,private dialog:MatDialog) {
            
     

     }

  ngOnInit(): void {

    this.ema=localStorage.getItem('email');

    this.acceptService.getPostDetailsByDate().subscribe(
      response =>
      {
        console.log(response)
        this.data=response
console.log("#######",response.dDate)
      }
    );


    // this.deliveryS.getDeliveryDetails().subscribe(response=>
    //   {
    //     console.log(response);
    //     this.deliverydata=response;
    //     console.log("%%%%%&&&",this.deliverydata);
        
        
    //   })


}




view(id:number){

  this.buttonDisabled=true;
  this.acceptService.setIdz(id);
  console.log(id);

  this.acceptService.getShipped(id).subscribe(

    response=>{
      this.acceptrequest=response;
      this.toast.success({detail:"success message",summary:"accepted",duration:5000})
    })
  

}

viewNo(id:number){

  this.acceptService.setIdz(id);
  console.log(id);

  this.acceptService.getDelivered(id).subscribe(

    response=>{
      this.acceptrequest=response;
      this.toast.success({detail:"success message",summary:"accepted",duration:5000})
    })
}




getValues(e:any, data:any) {
 
  if(e.target.checked){

    this.newArray.push(data);

  }else {
    let removeIndex = this.newArray.findIndex((itm) => itm===data);

    if(removeIndex !== -1)
      this.newArray.splice(removeIndex,1);
  }

  console.log("neweewww arrya",this.newArray);
  
}




bulk(id:number){
  this.acceptService.setIdz(this.data[0].postOrderId);
  console.log("8858585585",id);
  console.log("aaaaaaannnnnnnnnnnuuuuuuuuu",this.data[0].postOrderId);
  
  console.log("222222",this.acceptService.getIdz());
  
  this.acceptdetails.itemId=this.newArray;
  
console.log(this.acceptdetails.itemId);
this.acceptdetails.price=this.data[0].price;
console.log(this.acceptdetails.price);

this.acceptdetails.postorderId=this.data[0].postOrderId;
console.log(this.acceptdetails.postorderId);

this.itemS.setBulk(this.newArray).subscribe(
  response=>{
    this.bulkset=response;
    this.toast.success({detail:"success message",summary:"accepted",duration:5000})
    
  }
)

}




// bulk(id:number){
//   this.acceptService.setIdz(this.data[0].postOrderId);
//   console.log("8858585585",id);
//   console.log("aaaaaaannnnnnnnnnnuuuuuuuuu",this.data[0].postOrderId);
  
//   console.log("222222",this.acceptService.getIdz());
//   this.router.navigateByUrl("bulk-update");
  
// }



logout(){
  localStorage.clear();
  this.router.navigateByUrl('login')
}

show(){
  
  const dialogRef = this.dialog.open(DeliveryProfileComponent);
 
  dialogRef.afterClosed().subscribe(result => {
    console.log(`Dialog result: ${result}`);
   });
 }
}