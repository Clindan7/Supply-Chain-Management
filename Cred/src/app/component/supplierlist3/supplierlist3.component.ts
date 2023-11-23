import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { Observable } from 'rxjs';
import { SupplierDetails } from 'src/app/classes/supplier-details';
import { SupplierService } from 'src/app/services/supplier.service';

@Component({
  selector: 'app-supplierlist3',
  templateUrl: './supplierlist3.component.html',
  styleUrls: ['./supplierlist3.component.css']
})
export class Supplierlist3Component implements OnInit {

  private supplierDetails=new SupplierDetails;
  data: any;
  checkid: any;
  searchText:any;

  constructor(private supplierServices:SupplierService ,private router:Router,private toast:NgToastService) { }

  ngOnInit(): void {
    this.supplierServices.getSupplierDetails().subscribe(
      response =>
      {
        console.log(response)
        this.data=response
  
      }
    );
    
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


}