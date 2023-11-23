import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { PurchaserProfileComponent } from '../purchaser-profile/purchaser-profile.component';

@Component({
  selector: 'app-header2',
  templateUrl: './header2.component.html',
  styleUrls: ['./header2.component.css']
})
export class Header2Component implements OnInit {

  constructor(private router:Router,private dialog:MatDialog) { }

  ngOnInit(): void {
  }
  logout(){
    localStorage.clear();
    this.router.navigateByUrl('login')
  }

  show(){
  
    const dialogRef = this.dialog.open(PurchaserProfileComponent);
   
    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
     });
   }
}
