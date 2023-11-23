import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { PageEvent } from '@angular/material/paginator';


import { Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { AcceptDetails } from 'src/app/classes/accept-details';
import { SupplierDetails } from 'src/app/classes/supplier-details';
import { AcceptService } from 'src/app/services/accept.service';
import { DeliveryServiceService } from 'src/app/services/delivery-service.service';
import { ItemsService } from 'src/app/services/items.service';
import { PreorderService } from 'src/app/services/preorder.service';
import { SupplierService } from 'src/app/services/supplier.service';
import { CalenderComponent } from '../calender/calender.component';
import { SupplierProfileComponent } from '../supplier-profile/supplier-profile.component';

@Component({
  selector: 'app-supplier',
  templateUrl: './supplier.component.html',
  styleUrls: ['./supplier.component.css']
})
export class SupplierComponent implements OnInit {

  private SupplierDetails=new SupplierDetails;
  acceptDetails= new AcceptDetails;
  preorderdata: any;
  sortedData: any;
  postsortedData:any;
  deliveryList:any;
  name: any;
  Paginatedata:any;
  preorderdata2:any;
  postorderdata:any;
  postorderdata2:any;
  fItem: any;
  p:any=0;
  p1:any=1;
  q:any=0;
  q1:any=1;
  movies: any;
  len: any;
  item:any;
  acceptrequest:any;
  checkid: any;
  ema:any;
  e:any=0;
  searchText: any;
  buttonDisabled:boolean=false;
  count: any;
  invoicesent: any;

  constructor(private preorderService:PreorderService,
    private toast:NgToastService, private router:Router,
    private acceptS:AcceptService,private supplierService:SupplierService
    ,private fb:FormBuilder,private itemS:ItemsService,private deliveryS:DeliveryServiceService,
    private dialog: MatDialog,
    ) { }

  ngOnInit(): void {

    this.ema=localStorage.getItem('email');


    this.acceptS.getPostById().subscribe(
      response =>
      {
        console.log(response)
        this.postorderdata=response
        console.log("#######",this.postorderdata)
      }
    );


    this.acceptS.getPostDetailsbyid(this.acceptS.getIdz()).subscribe(
      response =>
      {
  
        this.postorderdata2=response
        console.log("++++++",this.postorderdata2);
  
        console.log(response.postOrderId)
           this.setDefaultAlternate(response.postOrderId);
  
      }
  
    );

    this.preorderService.getPreOrderDetails().subscribe(
      response =>
      {
        console.log(response)
        this.preorderdata=response
        this.len=this.preorderdata.length
      }
    );

    this.preorderService.preorderPagination(this.q,this.q1).subscribe(
      res=>{
        this.Paginatedata=res
      }
    )

    

  }




  rejectRequest(id:number){
    this.preorderService.deletePreOrderDetails(id).subscribe(
      response =>
      {
        this.toast.success({detail:"reject message",summary:" REJECTED",duration:5000})
        this.ngOnInit();
       
      }
    );
    this.toast.success({detail:"success message",summary:"supplier updated",duration:5000})
  }


  accept(id:number){
    this.acceptS.getAcceptRequestDelivery(id).subscribe(
  
      response=>{
        this.acceptrequest=response;
        this.toast.success({detail:"success message",summary:"accepted",duration:5000})
      })
    }
  




  search(name:any){

this.name = name.target.value;
this.ngOnInit()
}

filterItem(fItem:any){
console.log(fItem);
this.fItem=fItem;
this.ngOnInit()
}

logout(){

localStorage.clear();
this.router.navigateByUrl('login')
}



show(){
  
 const dialogRef = this.dialog.open(SupplierProfileComponent);

 dialogRef.afterClosed().subscribe(result => {
   console.log(`Dialog result: ${result}`);
  });
}


showCalender(){
  
  const dialogRef = this.dialog.open(CalenderComponent);
 
  dialogRef.afterClosed().subscribe(result => {
    console.log(`Dialog result: ${result}`);
   });
  // this.router.navigateByUrl('calender');
 }

showCsvPreorder(){
  console.log("##88899999999999999999999999999999")
  this.acceptS.displayCsv().subscribe({
   
    next:(response: any) => {
      {
      if(response)
      {
        this.toast.success({detail:"success message",summary:"File has been downloaded",duration:5000})
      }    
      console.log(response.body)
      let a = document.createElement('a');
      a.download = 'preorderlist.csv';
      a.href = window.URL.createObjectURL(new Blob([response.body], { type: 'application/csv;charset=cp932;' }));;
      a.click();
      }
      }
    })
}


displayStyle = "none";

acceptPreorderRequest(id:number){

this.displayStyle="block";

  this.preorderService.setId(id);

 
  this.form  =  this.fb.group({
    preOrderId: [''],
    itemId: [''],
    quantity: [''],
    address: [''],
    price: ['', Validators.required,Validators.pattern("[0-9]+")]
});




  this.preorderService.getPreOrderDetailsbyid(this.preorderService.getId()).subscribe(
    response =>
    {
      
      this.preorderdata2=response
      console.log("$$$$$$$$$$$$$$$",this.preorderdata2)
     this.checkid= console.log(this.preorderService.getId())
     this.setDefault(this.checkid);
    }
  );
  
  console.log(this.itemS.getId())
  this.itemS.getItemDetailsbyid(this.itemS.getId()).subscribe(
    response =>
    {
      
      this.item=response
      console.log(this.item)
     this.checkid= console.log(this.itemS.getId())
     
    }
  );
}

closePopup() {
  this.displayStyle = "none";
}

form= new FormGroup({
  preOrderId:new FormControl() ,
  itemId:new FormControl(),
  quantity: new FormControl(),
  address:new FormControl(),
  price:new FormControl()

});



acceptPreorder(form:FormGroup){

console.log(form)
     this.acceptDetails.preOrderId=this.form.value.preOrderId;
     this.acceptDetails.itemId=this.form.value.itemId;
     this.acceptDetails.quantity=this.form.value.quantity;
     this.acceptDetails.address=this.form.value.address;
     this.acceptDetails.price=this.form.value.price;

     console.log(this.acceptDetails)
     this.acceptS.saveAcceptDetails(this.acceptDetails).subscribe(  
      response => {  
         if(response)  
          {  
          
            this.toast.success({detail:"success message",summary:"accepted request successfully",duration:5000})

          }       
      },  
     );
  }
  setDefault(id:any)
  {
    console.log("function")
    console.log(this.preorderdata2)
    console.log(this.preorderdata2['itemId'])
    console.log(this.preorderdata2['address'])
    this.form = new FormGroup({
      preOrderId:new FormControl(this.preorderdata2['preOrderId']),
      itemId: new FormControl(this.preorderdata2['itemId']),
      address:new FormControl(this.preorderdata2['address']),
      quantity:new FormControl(this.preorderdata2['quantity']),
      price:new FormControl(this.preorderdata2['price'])

}
    )
}   



displayStyle2 = "none";

view(id:any) 
{
  this.displayStyle2="block";

  this.acceptS.setIdz(id);

 this.acceptS.getPostDetailsbyid(this.acceptS.getIdz()).subscribe(
    response =>
    {

      this.postorderdata2=response
console.log("++++++",this.postorderdata2);

      console.log(response.postOrderId)
         this.setDefaultAlternate(response.postOrderId);

    }

  );

  this.deliveryS.getDeliveryDetails().subscribe(
    response =>
    {
      this.deliveryList=response
      console.log(this.deliveryList)

    }

  );
}
closePopup2() {
  this.displayStyle2 = "none";
}


requestform= new FormGroup({
  itemName:new FormControl(),
  quantity: new FormControl(),
  address:new FormControl(),
 price:new FormControl(),
  supplier:new FormControl(),
  dDate: new FormControl(),
  deliveryPerson:new FormControl()

});

requestOrderForm(requestform:FormGroup){


  this.acceptDetails.itemName=this.requestform.value.itemName;
  //  this.acceptDetails.itemId=this.item.itemId
   
   this.acceptDetails.quantity=this.requestform.value.quantity;
   this.acceptDetails.address=this.requestform.value.address;

   this.acceptDetails.supplierName=this.requestform.value.supplier;
  //  this.acceptDetails.supplier.supplierId=this.form.value.supplier;
   this.acceptDetails.price=this.requestform.value.price;
  this.acceptDetails.dDate=this.requestform.value.dDate;
this.acceptDetails.dPersonId=this.requestform.value.deliveryPerson;

   console.log("!!!!!!!!!!!!!!!",this.acceptDetails)

console.log("000000++++=00000000",this.preorderdata[0].preOrderId);

console.log("000000**00000000",this.postorderdata[0].preorder.preOrderId);

  this.preorderService.updatePreOrderDetails(this.acceptDetails,this.postorderdata[0].preorder.preOrderId).subscribe(  
    response => {  
       if(response)  
        {  
  
          this.toast.success({detail:"success message",summary:"accepted request successfully",duration:5000})

            this.displayStyle2 = "none";
        }     

    },  
   );
   console.log("((((((((((");
   
}

setDefaultAlternate(id:number){
  this.requestform=new FormGroup({
    itemName:new FormControl(this.postorderdata2.preOrder.item['itemName']),
    quantity: new FormControl(this.postorderdata2.preOrder['quantity']),
    address:new FormControl(this.postorderdata2.preOrder['address']),
    price:new FormControl(this.postorderdata2['price']),
    supplier:new FormControl(this.postorderdata2.preOrder.supplier['supplierName']),
    dDate:new FormControl(this.postorderdata2.preOrder['dDate']),
    deliveryPerson:new FormControl(this.postorderdata2.preOrder['dPersonId'])
    })
}

paginate(a:any){


  this.acceptS.postorderPagination(this.p,this.p1).subscribe(
    response=>{
        this.postsortedData=response
        console.log("@@@@@@@@@@@@@@@@",this.postsortedData);
    }
  )
}

handlePageEvent(e: PageEvent) {
  this.q=e.pageIndex
  this.q1=e.pageSize
  this.preorderService.preorderPagination(this.q,this.q1).subscribe(
    res=>{
      this.Paginatedata=res
    }
  )
}

invoice(id:number){
  this.acceptS.getInvoice(id).subscribe(
  
    response=>{
      this.invoicesent=response;
      this.toast.success({detail:"success message",summary:"accepted",duration:5000})
    })
}
  }