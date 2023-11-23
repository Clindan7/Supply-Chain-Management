import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { PasswordDetails } from 'src/app/classes/password-details';
import { DeliveryServiceService } from 'src/app/services/delivery-service.service';
import { DeliveryProfileComponent } from '../delivery-profile/delivery-profile.component';

@Component({
  selector: 'app-change-deliverypassword',
  templateUrl: './change-deliverypassword.component.html',
  styleUrls: ['./change-deliverypassword.component.css']
})
export class ChangeDeliverypasswordComponent implements OnInit {

  ema:any;
  private passwordDetails=new PasswordDetails;
  constructor(private deliveryService:DeliveryServiceService,private toast:NgToastService,
    private router:Router,private dialog:MatDialog) { }

  ngOnInit(): void {
    this.ema=localStorage.getItem('email');
  }
  form = new FormGroup({
    oldpassword:new FormControl(),
    newpassword:new FormControl(),
    confirmpassword:new FormControl()
  
  });
  passwordForm(form:FormGroup){
this.passwordDetails.oldpassword=this.form.value.oldpassword;
this.passwordDetails.newpassword=this.form.value.newpassword;
this.passwordDetails.confirmpassword=this.form.value.confirmpassword;
this.deliveryService.changepasswordDelivery(this.passwordDetails).subscribe(
  response=>
  {
    this.toast.success({detail:"success message",summary:"supplier added",duration:5000})
     this.router.navigateByUrl("/admin");
  }
)
  }



  logout(){
    localStorage.clear();
    this.router.navigateByUrl('login')
  }
  
  search(name:any){

  }

  show(){
  
    const dialogRef = this.dialog.open(DeliveryProfileComponent);
   
    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
     });
   }
}



// submitSupplier(form:FormGroup){
//   console.log(this.data.userId)
//   this.supplierDetails.userId=this.data.userId;
//    this.supplierDetails.location=this.form.value.location;
//    this.supplierDetails.mobile=this.form.value.mobile;
//   this.supplierDetails.supplierName=this.data.name;
//    this.supplierService.saveSupplierDetails(this.supplierDetails).subscribe(
//    response =>
//    {
//     this.toast.success({detail:"success message",summary:"supplier added",duration:5000})
//      this.router.navigateByUrl("/login");
    
//    }
//   )}

