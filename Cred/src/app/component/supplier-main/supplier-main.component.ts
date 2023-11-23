import { Component, OnInit } from '@angular/core';
import { FormControl,FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { SupplierDetails } from 'src/app/classes/supplier-details';
import { UserDetails } from 'src/app/classes/user-details';
import { SupplierService } from 'src/app/services/supplier.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-supplier-main',
  templateUrl: './supplier-main.component.html',
  styleUrls: ['./supplier-main.component.css']
})
export class SupplierMainComponent implements OnInit {
  private supplierDetails=new SupplierDetails;
  private user=new UserDetails;
  data: any;
  userdata:any;
  checkid:any;
  
  constructor(private supplierService:SupplierService,private userService:UserService ,
    private router:Router,private toast:NgToastService) { }
  
  ngOnInit(): void {

 

    this.userService.getUserDetailsbyid(this.userService.getId()).subscribe(
      response =>
      {
    
        this.data=response
        console.log(this.data)
    console.log("userId")
    console.log(response.userId)
       this.setDefaultAlternate(response.userId);
      }
    );

    this.userService.getCurrentUser().subscribe(
      response =>
      {

        this.data=response
        console.log(this.data)
       this.checkid= console.log(this.userService.getId())
       this.setDefault(this.checkid);
      }
    );
    
  }



  form = new FormGroup({
    name:new FormControl(),
    email:new FormControl(),
    password:new FormControl(),
    location: new FormControl(),
    mobile:new FormControl()
  
  });

  addform=new FormGroup({
    name:new FormControl(),
    email:new FormControl()
  })

  

  submitSupplier(form:FormGroup){


    console.log(this.data.userId)
    this.supplierDetails.userId=this.data.userId;
     this.supplierDetails.location=this.form.value.location;
     this.supplierDetails.mobile=this.form.value.mobile;
    this.supplierDetails.supplierName=this.data.name;
     this.supplierService.saveSupplierDetails(this.supplierDetails).subscribe(
     response =>
     {
     


        if(response)  
      { 
        console.log(response);
        
        this.supplierService.setId(response.supplierId)
        console.log("jaaaaaaaa",response.supplierId);
      this.toast.success({detail:"success message",summary:"supplier added",duration:5000})
       this.router.navigateByUrl("/supplier-profilepic");
      
       }  


     }
    )}
  
  
  setDefault(id:any)
  {
   
    this.form = new FormGroup({
      name:new FormControl(this.data['name']),
      email: new FormControl(this.data['email']),
      password:new FormControl(this.data['password']),
      location:new FormControl(this.data['location']),
      mobile:new FormControl(this.data['mobile'])

}
    )
}  

setDefaultAlternate(id:any){
  this.form=new FormGroup({
    name:new FormControl(this.data['name']),
    email: new FormControl(this.data['email']),
    password:new FormControl(this.data['password']),
    location:new FormControl(this.data['location']),
    mobile:new FormControl(this.data['mobile'])
    })
}
}
