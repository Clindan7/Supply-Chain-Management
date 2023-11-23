import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { AcceptDetails } from 'src/app/classes/accept-details';
import { PostOrder } from 'src/app/classes/post-order';
import { AcceptService } from 'src/app/services/accept.service';
import { ItemsService } from 'src/app/services/items.service';
import { PreorderService } from 'src/app/services/preorder.service';
import { UserService } from 'src/app/services/user.service';


import {MatDialog, MatDialogConfig,MatDialogContent} from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { StatusComponent } from '../status/status.component';

@Component({
  selector: 'app-viewpost',
  templateUrl: './viewpost.component.html',
  styleUrls: ['./viewpost.component.css']
})
export class ViewpostComponent implements OnInit {
  id:any
  datas: any;
user:any;
data:any;
buttonDisabled:boolean=false;
postorders:any;
postorder:any;
  acceptrequest: any;
  rejectrequest: any;
  post: any;
  item:any;
  checkid:any;
  private acceptDetails =new  AcceptDetails;
private postOrder= new PostOrder;
  name: any;
  constructor(private itemService:ItemsService,
    private dialog: MatDialog,
    private preOrderService:PreorderService, 
    private acceptService:AcceptService, private toast:NgToastService,
     private router:Router,private userService:UserService
     ) { }

     
  
  ngOnInit(): void {

    this.id=localStorage.getItem('itemId')

    this.acceptService.getPostDetails().subscribe(
      response =>
      {
        // console.log(response)
        this.datas=response
          
      }
    );

    this.userService.getCurrentUser().subscribe(
      response=>{
        this.user=response;
      }
    )
    
console.log(this.itemService.getId())

this.getPostOrders();



  }

getPostOrders(){
  this.acceptService.getPostOrdersByQuery(this.id).subscribe(
    response=>{
      this.postorders=response;
      console.log("hoo",this.postorders);
      
    }
  )
}



acceptRequest(id:number){
console.log(id);
this.buttonDisabled=true;
  this.acceptService.getAcceptRequest(id).subscribe(

    response=>{
      this.acceptrequest=response;
      this.toast.success({detail:"success message",summary:"accepted",duration:5000})
    }
  )



}
rejectRequest(id:number){

  this.acceptService.deleteRejectRequest(id).subscribe(
    response=>{
      this.rejectrequest=response;
    }
  )
this.toast.error({detail:"reject message",summary:"rejected",duration:5000})
}


onClick(id:number){

  this.acceptService.setIdz(id);
  console.log(id);
  
  this.router.navigateByUrl("orderrequest");
}


status(id:any){
  this.itemService.setId(id);
  localStorage.setItem("itemId",id)
//  this.router.navigateByUrl("status");

 const dialogRef = this.dialog.open(StatusComponent);

 dialogRef.afterClosed().subscribe(result => {
   console.log(`Dialog result: ${result}`);
 });

// const dialogConfig = new MatDialogConfig();
// dialogConfig.disableClose = true;
// dialogConfig.id = "modal-component";
// dialogConfig.height = "300px";
// dialogConfig.width = "500px";
// const modalDialog = this.dialog.open(AaComponent,dialogConfig);

}

openDialog() {
  const dialogRef = this.dialog.open(StatusComponent);

  dialogRef.afterClosed().subscribe(result => {
    console.log(`Dialog result: ${result}`);
  });
}





displayStyle = "none";
  openPopup(id:any) {
  this.displayStyle="block"

  this.acceptService.setIdz(id);
  console.log(id);
  console.log("clintan+++++++++++++++++++++",this.acceptService.getIdz())
  this.acceptService.getPostDetailsbyid(this.acceptService.getIdz()).subscribe(
    response =>
    {

      this.data=response
      this.form.patchValue({
        itemName: this.data.preOrder.item.itemName,
        quantity:this.data.preOrder.quantity,
        address:this.data.preOrder.address,
    supplierName: this.data.preOrder.supplier.supplierName,
    price: this.data.price
      })
      console.log("clintan==============", this.data)
    }

  );

  this.itemService.getItemDetailsbyid(this.itemService.getId()).subscribe(
    response =>
    {
      
      this.item=response
      console.log(this.item)
     this.checkid= console.log(this.itemService.getId())
     
    }
  );



  }
  
  closePopup() {
  this.displayStyle = "none";
  }

  form= new FormGroup({
    
    itemName:new FormControl(),
    quantity: new FormControl(),
    address:new FormControl(),
   price:new FormControl(),
   supplierName:new FormControl(),
    dDate: new FormControl(),
  
  });

  onsubmitOrder(form:FormGroup){
    console.log(form)
    this.acceptDetails.itemName=this.form.value.itemName;
     this.acceptDetails.quantity=this.form.value.quantity;
     this.acceptDetails.address=this.form.value.address;
     this.acceptDetails.supplierName=this.form.value.supplierName;
     this.acceptDetails.price=this.form.value.price;
    this.acceptDetails.dDate=this.form.value.dDate;
     console.log(this.acceptDetails)


console.log(this.data.preOrder.preOrderId);

     this.preOrderService.updatePreOrderDetails(this.acceptDetails,this.data.preOrder.preOrderId).subscribe(  
      response => {  
         if(response)  
          {  
    
            this.toast.success({detail:"success message",summary:"accepted request successfully",duration:5000})
this.router.navigateByUrl("viewpost");
          }       
      },  
     );
  }


  search(name:any){
  
    this.name = name.target.value;
    this.ngOnInit()
    }
  }




























































































