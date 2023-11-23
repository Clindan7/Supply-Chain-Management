import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-purchaser-profile',
  templateUrl: './purchaser-profile.component.html',
  styleUrls: ['./purchaser-profile.component.css']
})
export class PurchaserProfileComponent implements OnInit {

  constructor(private userS:UserService) { }
data:any;
  ngOnInit(): void {

    this.userS.getCurrentUser().subscribe(
      response=>{
        this.data=response;
        console.log("!!!####",this.data);
        console.log("$$%%%",this.data.email);
        
      }
    )
  }

}
