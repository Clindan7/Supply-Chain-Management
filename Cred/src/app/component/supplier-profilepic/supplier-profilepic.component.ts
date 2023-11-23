import { Component, OnInit } from '@angular/core';
import { Route, Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { SupplierService } from 'src/app/services/supplier.service';

@Component({
  selector: 'app-supplier-profilepic',
  templateUrl: './supplier-profilepic.component.html',
  styleUrls: ['./supplier-profilepic.component.css']
})
export class SupplierProfilepicComponent implements OnInit {


  selectedFiles:any;
  currentFile:any;
  data:any;
  sup: any;
  id: any;
  constructor(private SupplierS:SupplierService,private router:Router,private toast:NgToastService) { }

  ngOnInit(): void {


    // this.SupplierS.getSupplierDetailsbyid(this.SupplierS.getId()).subscribe(
    //   response =>
    //   {
    //     console.log("(((((((((()_)))))))))))))",this.SupplierS.getId());
        
    //     this.data=response
    //  console.log("haaaahahahahaha",this.data[0]);
     
    //   }
    // );
    this.id=this.SupplierS.getId();
    
  }
  selectFile(event: any): void {
    this.selectedFiles = event.target.files;
  }

 upload()
  {
  
    if (this.selectedFiles) {
      const file: File | null = this.selectedFiles.item(0);

      if (file) {
        this.currentFile = file;
      }
     

     
  this.SupplierS.supplierImageUpload(this.currentFile,this.id).subscribe()
      this.router.navigateByUrl('login')
      this.toast.success({detail:"success message",summary:"successfully added",duration:5000})

}}


  
}
