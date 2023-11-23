import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { PasswordDetails } from 'src/app/classes/password-details';
import { PreorderService } from 'src/app/services/preorder.service';

@Component({
  selector: 'app-change-purchaserpassword',
  templateUrl: './change-purchaserpassword.component.html',
  styleUrls: ['./change-purchaserpassword.component.css']
})
export class ChangePurchaserpasswordComponent implements OnInit {

  ema:any;
  searchText:any;
  private passwordDetails=new PasswordDetails;
  constructor(private preorderService:PreorderService,private toast:NgToastService,
    private router:Router) { }

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
this.preorderService.changepasswordPurchaser(this.passwordDetails).subscribe(
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