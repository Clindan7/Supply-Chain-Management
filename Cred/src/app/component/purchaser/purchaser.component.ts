import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { Router } from '@angular/router';
import { NgToastComponent, NgToastService } from 'ng-angular-popup';
import { IDropdownSettings } from 'ng-multiselect-dropdown';
import { AcceptDetails } from 'src/app/classes/accept-details';
import { AcceptService } from 'src/app/services/accept.service';
import { ItemsService } from 'src/app/services/items.service';
import { PreorderService } from 'src/app/services/preorder.service';
import { SupplierService } from 'src/app/services/supplier.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-purchaser',
  templateUrl: './purchaser.component.html',
  styleUrls: ['./purchaser.component.css']
})
export class PurchaserComponent implements OnInit {
  items: any;
itemdata:any;
  admindata: any;
  user:any;
p:any=0;
p1:any=4;
  sortedData: any;
  fMovie: any;
  name: any;
  movies:any;
  fItem:any;
  len:any;
  
  data: any;
  checkid: any;
  supplierList:any;
  private acceptDetails =new  AcceptDetails;
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
  constructor(private itemServices:ItemsService,private supplierS:SupplierService, private userS:UserService,
    private router: Router,private userService:UserService,private accept:AcceptService,
    private preorderS:PreorderService,private toast:NgToastService) { }

  ngOnInit(): void {

    console.log("ooooooooooooooooooonnnnnnnnnnnn");

    console.log("iiiiiiiiiiii",this.fItem);


    this.itemServices.getitem(this.name,this.fItem).subscribe(response=>{
      this.movies=response;
      this.len=this.movies.length
console.log(this.movies[0].createDate)

  })
  


    this.itemServices.getItemDetails().subscribe(
      response =>
      {
        console.log(response)
        this.items=response
  
      }
    );


  
    
  }
  // itemName(itemName: any, filterItem: (fMovie: any) => void) {
  //   throw new Error('Method not implemented.');
  // }
  

  
  url="/home/clindan/items/item_pics/Screenshot from 2022-11-02 12-46-56.png"

  getProfilePic(): void {
    this.itemServices.getProfile().subscribe({
      next: (response: any) => {
        console.log(response.value);

        (document.getElementById('profilePicture') as HTMLImageElement).src =
          URL.createObjectURL(new Blob([response], { type: response.type }));
      },
      error(err) {
        console.log(err);
      },
    });
  }




    buy(id:any){ 
      console.log(id)
      this.itemServices.setId(id)
      this.router.navigate(['/viewpurchaser'])
    }

    view(id:any){
      // console.log(id)
       this.itemServices.setId(id);
       localStorage.setItem("itemId",id)

           this.router.navigateByUrl("viewpost");

    }

    status(id:any){

      this.itemServices.setId(id);
       localStorage.setItem("itemId",id)
      this.router.navigateByUrl("status");

    }



    paginate(a:any){


      this.itemServices.enrolledPagination(this.p,this.p1).subscribe(
        response=>{
            this.sortedData=response
            console.log(this.sortedData);
  
        }
      )
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

displayStyle = "none";


openPopup(id:any) {
 
  this.displayStyle="block"
  console.log(id)
  this.itemServices.setId(id)

  this.itemServices.getItemDetailsbyid(this.itemServices.getId()).subscribe(
    response =>
    {
      
      this.data=response
      console.log(this.data)
     this.checkid= console.log("ggggoooooookkkuuuu",this.itemServices.getId())
     
    }
  );
  
  console.log("***********",this.supplierS.getId())
  this.supplierS.getSupplierDetailsbyid(this.supplierS.getId()).subscribe(
    response =>
    {
      
      this.supplierList=response
      console.log("##########",this.supplierList)
     this.checkid= console.log(this.supplierS.getId())
     
    }
  );
  

  this.userService.getCurrentUser().subscribe(
    response =>
    {
      
      this.user=response
      console.log(this.user)
   
   
    }
  );

 
    this.supplierS.getSupplierDetails().subscribe(
      response =>
      {
        this.supplierList=response
        console.log("@@@@@@@@@@@@@",this.supplierList)
  
      }
  
    );


}
closePopup() {
  this.displayStyle = "none";
}

form = new FormGroup({
  quantity:new FormControl('',Validators.required),
  address: new FormControl('',Validators.required),
  supplier:new FormControl(),


});


onsubmit(form: FormGroup) {
  this.acceptDetails.itemId = this.itemServices.getId();
console.log(this.form.value.supplier)
this.acceptDetails.quantity = this.form.value.quantity;
this.acceptDetails.address = this.form.value.address;
this.acceptDetails.userId = this.user.userId;

this.form.value.supplier.forEach((element: any) => {
this.acceptDetails.supplierId = element.supplierId;
  let obj={
    "address":this.acceptDetails.address,
    "quantity":this.acceptDetails.quantity,
    "itemId":this.acceptDetails.itemId,
    "supplierId":this.acceptDetails.supplier,
    "userId":this.acceptDetails.userId
  }

this.preorderS.savePreOrderDetails(this.acceptDetails).subscribe(
  response => {
    if (response) {
      this.toast.success({ detail: "success message", summary: "request sent", duration: 5000 })

    }
  },
 
);

});
}
}











   