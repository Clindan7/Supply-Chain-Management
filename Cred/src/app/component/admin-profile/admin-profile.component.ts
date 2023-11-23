import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-admin-profile',
  templateUrl: './admin-profile.component.html',
  styleUrls: ['./admin-profile.component.css']
})
export class AdminProfileComponent implements OnInit {
data:any;
  constructor(private userS:UserService) { }

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
