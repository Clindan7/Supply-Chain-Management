import { HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { Observable } from 'rxjs';
import { SupplierDetails } from 'src/app/classes/supplier-details';
import { SupplierService } from 'src/app/services/supplier.service';
import { AdminProfileComponent } from '../admin-profile/admin-profile.component';


@Component({
  selector: 'app-supplierlist',
  templateUrl: './supplierlist.component.html',
  styleUrls: ['./supplierlist.component.css']
})
export class SupplierlistComponent implements OnInit {
searchText: any;
search($event: Event) {
throw new Error('Method not implemented.');
}

  private supplierDetails=new SupplierDetails;
  data: any;
  checkid: any;
  ema: any;


  constructor(private supplierServices:SupplierService, private dialog:MatDialog, private router:Router,private toast:NgToastService) { }

  ngOnInit(): void {
    this.supplierServices.getSupplierDetails().subscribe(
      response =>
      {
        console.log(response)
        this.data=response
  
      }
    );
    
    this.ema=localStorage.getItem('email');
  }
  form = new FormGroup({
    supplierName:new FormControl(),
    location: new FormControl(),
    mobile:new FormControl()
  
  });

  updateForm = new FormGroup({
    itemName:new FormControl(),
    description: new FormControl(),
    type:new FormControl()
  
  });



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


navigateToUpdate(supplierId:any){
  this.supplierServices.setId(supplierId);
  this.router.navigateByUrl("supplier-update");

}

logout(){
  localStorage.clear();
  this.router.navigateByUrl('login')
}

show(){
  
  const dialogRef = this.dialog.open(AdminProfileComponent);
 
  dialogRef.afterClosed().subscribe(result => {
    console.log(`Dialog result: ${result}`);
   });
 }
 
  
}