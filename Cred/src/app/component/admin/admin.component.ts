import { HttpClient, HttpEventType, HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { ItemDetails } from 'src/app/classes/item-details';
import { ItemsService } from 'src/app/services/items.service';
import { SupplierService } from 'src/app/services/supplier.service';
import { AdminProfileComponent } from '../admin-profile/admin-profile.component';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  data:any;
  p:any=0;
  dataz:any;
  p1:any=3;
  sortedData: any;
  name: any;
  fileName = '';
  fItem: any;
  itemlist: any;
  selectedFiles:any;
  fileToUpload:any;
  len: any;
  progress: any;
  currentFile: any;
  message: any;
  checkid: any;
  itemDetails=new ItemDetails;
ema: any;
  constructor(private itemService:ItemsService,
    private http:HttpClient, private supplierS:SupplierService,
     private router:Router,private toast:NgToastService,private dialog:MatDialog) { }

  ngOnInit(): void {


    this.ema=localStorage.getItem('email');
    console.log("*********",this.ema);
    
    console.log("ooooooooooooooooooonnnnnnnnnnnn");

    console.log("iiiiiiiiiiii",this.fItem);


    this.itemService.getitem(this.name,this.fItem).subscribe(response=>{
      this.itemlist=response;
      this.len=this.itemlist.length
console.log(this.itemlist[0].createDate)

  })

    this.itemService.getItemDetails().subscribe(
      response =>
      {
        console.log(response)
        this.data=response
  
      }
    );




    this.supplierS.getSupplierDetails().subscribe(
      response =>
      {
        console.log(response)
        this.dataz=response
  
      }
    );
  }

  form = new FormGroup({
    itemName:new FormControl(),
    description: new FormControl(),
    type:new FormControl()
  
  });
  
  deleteItem(id:number)
  {
    this.itemService.deleteItemDetails(id).subscribe(
      response =>
      {
        this.toast.success({detail:"success message",summary:"successfully updated",duration:5000})
      
        this.ngOnInit();
      }
    );
  }

  logout(){
    localStorage.clear();
    this.router.navigateByUrl('login')
  }


paginate(a:any){


  this.itemService.enrolledPagination(this.p,this.p1).subscribe(
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

selectFile(event:any) {
  this.selectedFiles = event.target.files;
}


upload() {
  this.progress = 0;

  this.currentFile = this.selectedFiles.item(0);
  this.itemService.upload(this.currentFile).subscribe(
    event => {
      if (event.type === HttpEventType.UploadProgress) {
       // this.progress = Math.round(100 * event.loaded / event.total);
       this.toast.success({detail:"Success ",summary:"Successfully uploaded",duration:5000,position:'br'})
    

      } else if (event instanceof HttpResponse) {
        this.message = event.body.message;
        this.toast.success({detail:"Success ",summary:"Successfully uploaded",duration:5000,position:'br'})
        
       // this.fileInfos = this.uploadService.getFiles();
      }
    },
    err => {
      this.progress = 0;
      this.message = 'Could not upload the file!';
      this.toast.error({detail:"Failure ",summary:"couldnot upload" ,duration:5000,position:'br'})
      this.currentFile = undefined;
    });

  this.selectedFiles = undefined;
}


showItemCsv(){
  console.log("##88899999999999999999999999999999")
  this.itemService.displayItemCsv().subscribe({
   
    next:(response: any) => {
      {
      if(response)
      {
        this.toast.success({detail:"success message",summary:"File has been downloaded",duration:5000})
      }    
      console.log(response.body)
      let a = document.createElement('a');
      a.download = 'itemlist.csv';
      a.href = window.URL.createObjectURL(new Blob([response.body], { type: 'application/csv;charset=cp932;' }));;
      a.click();
      }
      }
    })
}




displayStyle = "none";

  openPopup(id:any) {

    this.displayStyle="block";
    this.itemService.setId(id);
    console.log(id);
    
    this.itemService.getItemDetailsbyid(this.itemService.getId()).subscribe(
      response =>
      {
        
        this.data=response
        console.log(this.data)
       this.checkid= console.log(this.itemService.getId())
       this.setDefault1(this.checkid);
      }
    );
    

  }

  closePopup() {
    this.displayStyle = "none";
  }

  
  updateform = new FormGroup({
    itemName:new FormControl(),
    description: new FormControl(),
    type:new FormControl()
  
  });

  itemupdate(updateform:FormGroup)
  {
     this.itemDetails.itemName=this.updateform.value.itemName;
     this.itemDetails.description=this.updateform.value.description;
     this.itemDetails.type=this.updateform.value.type
     
     console.log(this.itemService.getId())
    
     this.itemService.updateItemDetails(this.itemDetails,this.itemService.getId()).subscribe(
      response => {  
        if(response)
        {
          this.router.navigateByUrl('profile-update')
          this.toast.success({detail:"success message",summary:"successfully updated",duration:5000})
         
        }
      });
    

    }


    
  setDefault1(id:any)
  {

    this.updateform = new FormGroup({
      itemName:new FormControl(this.data['itemName']),
      description: new FormControl(this.data['description']),
      type:new FormControl(this.data['type'])
 
}
    )

}



show(){
  
  const dialogRef = this.dialog.open(AdminProfileComponent);
 
  dialogRef.afterClosed().subscribe(result => {
    console.log(`Dialog result: ${result}`);
   });
 }
 
}











