  import { Component, OnInit } from '@angular/core';
  import { FormControl, FormGroup } from '@angular/forms';
  import { Router } from '@angular/router';
  import { NgToastService } from 'ng-angular-popup';
  import { DeliveryDetails } from 'src/app/classes/delivery-details';
  import { UserDetails } from 'src/app/classes/user-details';
  import { DeliveryServiceService } from 'src/app/services/delivery-service.service';
  import { UserService } from 'src/app/services/user.service';

  @Component({
    selector: 'app-delivery-person',
    templateUrl: './delivery-person.component.html',
    styleUrls: ['./delivery-person.component.css']
  })
  export class DeliveryPersonComponent implements OnInit {

    data: any;
    checkid: any;
    private deliverydetails=new DeliveryDetails;
    private user=new UserDetails;
    constructor( private toast:NgToastService, 
      private deliveryServiceService:DeliveryServiceService, private router:Router, private userService:UserService) { }

    ngOnInit(): void {

      this.userService.getUserDetailsbyid(this.userService.getId()).subscribe(
        response =>
        {
      
          this.data=response
          console.log(this.data)
      console.log("userId")
      console.log(response.userId)
        this.setDefaultAlternate(response.userId);
        }
      );

      this.userService.getCurrentUser().subscribe(
        response =>
        {

          this.data=response
          console.log(this.data)
        this.checkid= console.log(this.userService.getId())
        this.setDefault(this.checkid);
        }
      );
    }
  
    form = new FormGroup({
      name:new FormControl(),
      email:new FormControl(),
      password:new FormControl(),
      route: new FormControl(),
      mobile:new FormControl()
    
    });

    addform=new FormGroup({
      name:new FormControl(),
      email:new FormControl()
    })
    

    submitD(form:FormGroup){
      console.log(this.data.userId)
      this.deliverydetails.userId=this.data.userId;
      this.deliverydetails.dPersonName=this.data.name;
      this.deliverydetails.mobile=this.form.value.mobile;

      this.deliverydetails.route=this.form.value.route;
      this.deliveryServiceService.saveDeliveryDetails(this.deliverydetails).subscribe(
      response =>
      {
        this.toast.success({detail:"success message",summary:"supplier added",duration:5000})
        this.router.navigateByUrl("/login");
        
      }
      )}

      setDefault(id:any)
      {
        console.log("function")
  
          this.form = new FormGroup({
          name:new FormControl(this.data['name']),
          email: new FormControl(this.data['email']),
          password:new FormControl(this.data['password']),
          mobile:new FormControl(this.data['mobile']),
          route:new FormControl(this.data['route'])
    
    }
        )
    }  
    
    setDefaultAlternate(id:any){
      this.form=new FormGroup({
        name:new FormControl(this.data['name']),
        email: new FormControl(this.data['email']),
        password:new FormControl(this.data['password']),
        mobile:new FormControl(this.data['mobile']),
        route:new FormControl(this.data['route'])
        })
    }

    }

