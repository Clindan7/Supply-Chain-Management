import { Component, OnInit } from '@angular/core';
import { NgToastService } from 'ng-angular-popup';
import { AcceptService } from 'src/app/services/accept.service';
import { UserService } from 'src/app/services/user.service';



@Component({
  selector: 'app-purchaser-orderlist',
  templateUrl: './purchaser-orderlist.component.html',
  styleUrls: ['./purchaser-orderlist.component.css']
})
export class PurchaserOrderlistComponent implements OnInit {
data:any;
view:any;
searchText:any;
  constructor(private acceptService:AcceptService,private toast:NgToastService,private userS:UserService
  ) { }

  ngOnInit(): void {

    this.acceptService.getPostDetailsByDate().subscribe(
      response =>
      {
        console.log(response)
        this.data=response
console.log("#######",response.dDate)
      }
    );




  }

  showCsv(){
    console.log("##88899999999999999999999999999999")
    this.acceptService.displayPreorderCsv().subscribe({
     
      next:(response: any) => {
        {
        if(response)
        {
          this.toast.success({detail:"success message",summary:"File has been downloaded",duration:5000})
        }    
        console.log(response.body)
        let a = document.createElement('a');
        a.download = 'employees.csv';
        a.href = window.URL.createObjectURL(new Blob([response.body], { type: 'application/csv;charset=cp932;' }));;
        a.click();
        }
        }
      })
  }

}
