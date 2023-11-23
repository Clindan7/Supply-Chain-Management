import { HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { Observable } from 'rxjs';
import { SupplierDetails } from 'src/app/classes/supplier-details';
import { SupplierService } from 'src/app/services/supplier.service';
import { SupplierProfileComponent } from '../supplier-profile/supplier-profile.component';

@Component({
  selector: 'app-supplierlist2',
  templateUrl: './supplierlist2.component.html',
  styleUrls: ['./supplierlist2.component.css']
})
export class Supplierlist2Component implements OnInit {


  private supplierDetails=new SupplierDetails;
  data: any;
  checkid: any;
data2:any;
  ema: any;
searchText: any;

  constructor(private supplierServices:SupplierService ,
    private router:Router,private toast:NgToastService,private dialog:MatDialog) { }

  ngOnInit(): void {
    this.ema=localStorage.getItem('email');
    
    this.supplierServices.getSupplierDetails().subscribe(
      response =>
      {
        console.log(response)
        this.data=response
  
      }
    );

  
  }

  search($event: Event) {
    throw new Error('Method not implemented.');
    }
    
    logout() {
      localStorage.clear();
      this.router.navigateByUrl('login')
    }



  deleteSupplierDetails(id:number)
  {
    this.supplierServices.deleteSupplierDetails(id).subscribe(
      response =>
      {
        this.toast.success({detail:"success message",summary:"deletion success",duration:5000})
        this.ngOnInit();
           }
    );
  }




displayStyle = "none";

navigateToUpdate(supplierId:any) {
 
  this.displayStyle="block";

  this.supplierServices.setId(supplierId);
  console.log(this.supplierServices.getId())
  this.supplierServices.getSupplierDetailsbyid(this.supplierServices.getId()).subscribe(
    response =>
    {
      
      this.data2=response
      console.log(this.data2)
     this.checkid= console.log(this.supplierServices.getId())
     this.setDefault(this.checkid);
     console.log("check",this.setDefault(this.checkid));
     
    }
  );

}
closePopup() {
  this.displayStyle = "none";
}


updateForm = new FormGroup({
    
  supplierName:new FormControl(),
  location: new FormControl(),
  mobile:new FormControl()

});

updateSupplier(updateForm:FormGroup){

  this.supplierDetails.supplierName=this.updateForm.value.supplierName;
  this.supplierDetails.location=this.updateForm.value.location;
  this.supplierDetails.mobile=this.updateForm.value.mobile;
  
 
  this.supplierServices.updateSupplierDetails(this.supplierDetails,this.supplierServices.getId()).subscribe(  
   response => {  
      if(response)  
       {  
        this.toast.success({detail:"success message",summary:"supplier updated",duration:5000})
         
       }  
       
   },  
  

);

}

setDefault(id:any)
{
  console.log("haaaaaaaaai");
  
  console.log("function")
  this.updateForm = new FormGroup({
    supplierName:new FormControl(this.data2['supplierName']),
    location: new FormControl(this.data2['location']),
    mobile:new FormControl(this.data2['mobile'])

}
  )
}


show(){
  
  const dialogRef = this.dialog.open(SupplierProfileComponent);
 
  dialogRef.afterClosed().subscribe(result => {
    console.log(`Dialog result: ${result}`);
   });
 }
}