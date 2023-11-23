import { Component, OnInit } from '@angular/core';
import { DeliveryServiceService } from 'src/app/services/delivery-service.service';

@Component({
  selector: 'app-delivery-profile',
  templateUrl: './delivery-profile.component.html',
  styleUrls: ['./delivery-profile.component.css']
})
export class DeliveryProfileComponent implements OnInit {
deliverydata:any;
  constructor(private deliveryS:DeliveryServiceService) { }

  ngOnInit(): void {

    this.deliveryS.getDeliveryDetails().subscribe(response=>
      {
        console.log(response);
        this.deliverydata=response;
        console.log("%%%%%&&&",this.deliverydata);
        
        
      })
  }

}
