import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { ItemsService } from 'src/app/services/items.service';

@Component({
  selector: 'app-profile-update',
  templateUrl: './profile-update.component.html',
  styleUrls: ['./profile-update.component.css']
})
export class ProfileUpdateComponent implements OnInit {

  selectedFiles:any;
  currentFile:any;
  data:any;
  constructor(private itemServices:ItemsService,private router:Router,private toast:NgToastService) { }

  ngOnInit(): void {

    this.itemServices.getItemDetailsbyid(this.itemServices.getId()).subscribe(
      response =>
      {
        
        this.data=response
        console.log(this.data)
     
      }
    );
    
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
     
      
     
  this.itemServices.ItemImageUpdate(this.currentFile,this.data.itemId).subscribe()
      this.router.navigateByUrl('admin')
      this.toast.success({detail:"success message",summary:"successfully added",duration:5000})

}}

logout(){

  localStorage.clear();
  this.router.navigateByUrl('login')
  }
  
}
