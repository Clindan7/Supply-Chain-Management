import { Component, OnInit } from '@angular/core';
import { UserDetails } from 'src/app/classes/user-details';
import { UserService } from 'src/app/services/user.service';

import {  FormGroup, FormControl,  } from '@angular/forms';
import { Router } from '@angular/router';
@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  private userDetails= new UserDetails();

  constructor(private router:Router,private userService:UserService) { }

  ngOnInit(): void {



  }
  form = new FormGroup({
    name: new FormControl(),
    email: new FormControl(),
    password: new FormControl(),
    role: new FormControl(),
  });
  
  onSubmit(form: FormGroup)
    {
       let name=this.form.value.name;
       console.log(this.form.value.name);
       let email=this.form.value.email;
       let password=this.form.value.password;
       let role=this.form.value.role;
       this.userDetails.name = this.form.value.name; 
       this.userDetails.email = this.form.value.email; 
       this.userDetails.password = this.form.value.password;
       this.userDetails.role = this.form.value.role;
       this.userService.saveUserDetails(this.userDetails).subscribe(  
      response => {  
            if(response)  
            {  
              if(role==1){

              this.userService.setId(response.userId)
              this.router.navigateByUrl("supplier-main")
              }
              else if(role==3){

                this.userService.setId(response.userId)
                console.log("frrrv")
                this.router.navigateByUrl("delivery")
                console.log("anuak")
              
               
            
            }  
              else
              this.router.navigateByUrl("login");
            }  
            else  
            {  
                alert("Sign up failed.")  
            }  
        },  
       

);

    }
}
