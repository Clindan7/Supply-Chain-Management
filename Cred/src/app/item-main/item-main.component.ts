import { Component, OnInit } from '@angular/core';
import { FormGroup ,FormControl} from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { ItemDetails } from 'src/app/classes/item-details';
import { ItemsService } from 'src/app/services/items.service';
import { AdminProfileComponent } from '../component/admin-profile/admin-profile.component';

@Component({
  selector: 'app-item-main',
  templateUrl: './item-main.component.html',
  styleUrls: ['./item-main.component.css']
})
export class ItemMainComponent implements OnInit {
ema: any;
search //     error(err:any) {
($event: Event) {
throw new Error('Method not implemented.');
}

  private itemDetails=new ItemDetails;
  data: any;

  savedImage: any;
  file:any
  selectedFiles: any;
  currentFile: any;

  constructor(private itemServices:ItemsService,private dialog:MatDialog, private router:Router,private toast:NgToastService) { }

  ngOnInit(): void {


    this.ema=localStorage.getItem('email');

  }

form = new FormGroup({
  itemName:new FormControl(),
  description: new FormControl(),
  type:new FormControl(),
});

  submitItem(form:FormGroup)
  {
    
     this.itemDetails.itemName=this.form.value.itemName;
     console.log("hiiiii")
     this.itemDetails.description=this.form.value.description;
     this.itemDetails.type=this.form.value.type
     console.log(this.itemDetails)
     this.itemServices.saveItemDetails(this.itemDetails).subscribe(  
      response => {  
        // this.itemDetails.photos=response.photos;
        // this.file=response.photos;
        // console.log(this.file)
        // this.uploadProfilePic(response.itemId);
        // this.onChange(this.form.value.photos,response.itemId)
        
         if(response)  
          {  
            this.itemServices.setId(response.itemId)
            this.router.navigateByUrl('profile')
            this.toast.success({detail:"success message",summary:"successfully added",duration:5000})
            // this.router.navigateByUrl("/itemlist");
          }       
      },  
     );
  }
  show(){
  
    const dialogRef = this.dialog.open(AdminProfileComponent);
   
    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
     });
   }
   

  logout(){
    localStorage.clear();
    this.router.navigateByUrl('login')
  }

  // onChange(photos:any,itemId:any) {
  //   this.file = photos;
  //   console.log('Changed');
  //   this.uploadProfilePic(itemId);
  // }


  // uploadProfilePic(id:any): void {
  //   let data = new FormData();
  //   console.log(this.file)
  //   data.append('photos', this.file);
  //   console.log(data)
  //   this.itemServices.uploadProfile(data,id).subscribe({
  //     next: (response: any) => {
  //       console.log(response);
  //       this.getProfilePic();
  //     },
  //     error(err:any) {
  //       console.log(err);
  //     }, 
  //   });
  // }

  getProfilePic(): void {
    this.itemServices.getProfile().subscribe({
      next: (response: any) => {
        console.log(response.value);

        (document.getElementById('profilePicture') as HTMLImageElement).src =
          URL.createObjectURL(new Blob([response], { type: response.type }));
      },
      error(err) {
        console.log(err);
      },
    });
  }
//   selectFile(event: any): void {
//     this.selectedFiles = event.target.files;
//   }

//  upload()
//   {
  
//     if (this.selectedFiles) {
//       const file: File | null = this.selectedFiles.item(0);

//       if (file) {
//         this.currentFile = file;
//       }
     
//       // this.assi.file=this.currentFile;
     
//   this.itemServices.AssignmentUpload(this.currentFile).subscribe(
//     response =>{
//       console.log(response)
//       if(response)
//       {
//         alert("ASSIGNMENT SUBMITTED SUCCESSFULLY")
//       }
//       else
//       {
//         alert("ASSIGNMENT SUBMITTION FAILED")
//       }

//     }
//   )

//   }}
}
