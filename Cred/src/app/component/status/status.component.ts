import { Component, OnInit } from '@angular/core';
import { ItemDetails } from 'src/app/classes/item-details';
import { AcceptService } from 'src/app/services/accept.service';
import { ItemsService } from 'src/app/services/items.service';

@Component({
  selector: 'app-status',
  templateUrl: './status.component.html',
  styleUrls: ['./status.component.css']
})
export class StatusComponent implements OnInit {
  status:any;
  post: any;
  private itemDetails= new ItemDetails;
  id: any;
  constructor(private itemservice:ItemsService, private acceptService:AcceptService,
    ) { }


  ngOnInit(): void {
  
    this.acceptService.getPostDetailsStatus(this.id).subscribe(
      response =>
      {
        console.log(response)
        this.post=response
          
      }
    );

    this.id=localStorage.getItem('itemId')

 
    this.acceptService.getPostDetailsStatus(this.id).subscribe(
      response=>{
   
        console.log(this.itemDetails.itemId);
        
        this.post = response;
        console.log("++++++++++++++++++++++++++++++",this.post[0].orderStatus)
      }
    )



  }

  
}