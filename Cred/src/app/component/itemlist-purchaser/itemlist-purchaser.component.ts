import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { Route, Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { AcceptDetails } from 'src/app/classes/accept-details';
import { ItemDetails } from 'src/app/classes/item-details';
import { ItemsService } from 'src/app/services/items.service';
import { PreorderService } from 'src/app/services/preorder.service';
import { SupplierService } from 'src/app/services/supplier.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-itemlist-purchaser',
  templateUrl: './itemlist-purchaser.component.html',
  styleUrls: ['./itemlist-purchaser.component.css']
})
export class ItemlistPurchaserComponent implements OnInit {
  // private itemDetails=new ItemDetails;
data:any;
acceptDetails=new AcceptDetails;
item:any;
user:any;
fItem:any;
p:any=0;
p1:any=3;
form:FormGroup;
items:any;
isChecked: any;
  supplierList: any;
  sortedData: any;
  name: any;
  itemlist: any;
  len: any;
  col: any;
  quantity:any[]=[];
  quantities:any[]=[];
  quantities2:any[]=[];

  constructor(private fb:FormBuilder,private preorderS:PreorderService,private toast:NgToastService,
    private userService:UserService, private supplierServices:SupplierService, private itemServices:ItemsService,
    private router:Router) { 

      
      this.form=this.fb.group({
        checkArray:this.fb.array([
          
        ]),
      })
      
    }



  ngOnInit(): void {

  
    console.log("ooooooooooooooooooonnnnnnnnnnnn");

    console.log("iiiiiiiiiiii",this.fItem);


    this.itemServices.getitem(this.name,this.fItem).subscribe(response=>{
      this.itemlist=response;
      this.len=this.itemlist.length
console.log(this.itemlist[0].createDate)

  })


    this.itemServices.getItemDetails().subscribe(
      response =>
      {
        console.log(response)
        this.data=response
  
      }
    );
  }
  
onCheckboxChange(e:any,index:any){
  
  let j:any;

  const checkArray:FormArray=this.form.get('checkArray') as FormArray;
  if(e.target.checked){
    checkArray.push(new FormControl(Number(e.target.value)));
    this.quantities2[index]=this.quantities[index];    
    console.log("re",this.quantities2);
    
  }
else{
  var i=0;
  checkArray.controls.forEach((item:any) => {
if(item.value==e.target.value){
  checkArray.removeAt(i);
  if(i !== -1)
  this.quantities.splice(i,1);
  return;
}
});
  
  
  i++;
}

}
yesForm = new FormGroup({
  quantity: new FormControl()
});

addQuantity(e:any,index:any){
    this.quantities[index]=e.target.value;
  console.log("u",this.quantities);
  this.quantities2[index]="";
  console.log("we",this.quantities2);
  }
remove(i:any){
  this.quantities[i]="";
  console.log(this.quantities); 
}


displayStyle = "none";
  submitForm(yesForm:FormGroup){
    this.quantities2=this.quantities2.filter((element) => {
      return element !== "";
    })
    this.yesForm.value.quantity=this.quantities2;
    this.displayStyle="block"
    this.itemServices.setItems(this.form.value)
    this.items=this.form.value;


    this.acceptDetails.quantity=this.yesForm.value.quantity;
    console.log("itemarray##########",this.acceptDetails.quantity);
    
    this.col = this.itemServices.getItems();
    console.log("ccoooooooollllll",this.col);
    
      this.itemServices.sendCollection(this.col.checkArray).subscribe(
        response => {
        
          this.data = response;
          console.log(this.data);
          

        }
      );

      
 

    this.userService.getCurrentUser().subscribe(
      response => {

        this.user = response
        console.log(this.user)


      }
    );


    this.supplierServices.getSupplierDetails().subscribe(
      response => {
        this.supplierList = response
        console.log(this.supplierList)

      }

    );






  }

  closePopup() {
    this.displayStyle = "none";

  }

  itemlistform = new FormGroup({
    quantity: new FormControl(),
    address: new FormControl(),
    supplier: new FormControl(),


  });
 

  onsubmit(itemlistform: FormGroup) {

    console.log(this.itemlistform.value.supplier)
    this.acceptDetails.quantity = this.acceptDetails.quantity;
    this.acceptDetails.address = this.itemlistform.value.address;
    this.acceptDetails.supplierId = this.itemlistform.value.supplier;
    console.log(this.acceptDetails.supplierId)
    this.acceptDetails.userId = this.user.userId;
    this.acceptDetails.itemId=this.col.checkArray;

    // this.acceptDetails.itemId = this.itemServices.getId();




    console.log(this.acceptDetails)
    this.preorderS.savePreOrderDetailsArray(this.acceptDetails).subscribe(
      response => {
        if (response) {
          this.toast.success({ detail: "success message", summary: "request sent", duration: 5000 });
          this.displayStyle = "none";
        }
      },
    );
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


}
