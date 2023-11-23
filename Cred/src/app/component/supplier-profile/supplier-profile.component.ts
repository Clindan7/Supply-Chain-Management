import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { SupplierDetails } from 'src/app/classes/supplier-details';
import { SupplierService } from 'src/app/services/supplier.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-supplier-profile',
  templateUrl: './supplier-profile.component.html',
  styleUrls: ['./supplier-profile.component.css']
})
export class SupplierProfileComponent implements OnInit {
supplierDetails=new SupplierDetails;
data:any;
data1:any;
  checkid: any;
  suppliers:any;
  user: any;
  userbyid: any;
  constructor(private userService:UserService,private supplierService:SupplierService) { }

  ngOnInit(): void {


this.userService.getCurrentUser().subscribe(
  response=>{
    this.data=response;
    console.log("!!!####",this.data);
    console.log("$$%%%",this.data.email);
    
  }
)




    this.supplierService.getSuppliers().subscribe(
      response =>
      {
        
        this.suppliers=response
        console.log("@@@@@@@@@@!@!",this.suppliers)
        console.log(this.suppliers.supplierName);
        
       
      }
    );

}



    


   
}
