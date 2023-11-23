
import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';

import { Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { filter } from 'rxjs';
import { AcceptDetails } from 'src/app/classes/accept-details';
import { ItemDetails } from 'src/app/classes/item-details';
import { ItemsService } from 'src/app/services/items.service';
import { PreorderService } from 'src/app/services/preorder.service';

import { SupplierService } from 'src/app/services/supplier.service';
import { UserService } from 'src/app/services/user.service';
@Component({
  selector: 'app-viewpurchaser2',
  templateUrl: './viewpurchaser2.component.html',
  styleUrls: ['./viewpurchaser2.component.css']
})
export class Viewpurchaser2Component implements OnInit {


  supplierList: any;
  data: any;
  itemId: any;
  user: any;
  checkid: any;
  i: any;
  col: any;
  s: any;

  private acceptDetails = new AcceptDetails;

  constructor(private supplierServices: SupplierService,
    private router: Router, private userService: UserService,
    private preOrderService: PreorderService, private toast: NgToastService,
    private itemServices: ItemsService, private fb:FormBuilder) { 
    }
   
  

  ngOnInit(): void {



    this.col = this.itemServices.getItems();
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


  

  form = new FormGroup({
    quantity: new FormControl(),
    address: new FormControl(),
    supplier: new FormControl(),


  });
 

  onsubmit(form: FormGroup) {

    console.log(this.form.value.supplier)
    this.acceptDetails.quantity = this.form.value.quantity;
    this.acceptDetails.address = this.form.value.address;
    this.acceptDetails.supplierId = this.form.value.supplier;
    console.log(this.acceptDetails.supplierId)
    this.acceptDetails.userId = this.user.userId;
    this.acceptDetails.itemId=this.col.checkArray;

    // this.acceptDetails.itemId = this.itemServices.getId();




    console.log(this.acceptDetails)
    this.preOrderService.savePreOrderDetailsArray(this.acceptDetails).subscribe(
      response => {
        if (response) {
          this.toast.success({ detail: "success message", summary: "request sent", duration: 5000 })

        }
      },
    );
  }








}