import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { OtpDetails } from 'src/app/classes/otp-details';
import { UserService } from 'src/app/services/user.service';


@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent implements OnInit {

  

  otp = new OtpDetails();

  constructor(private router:Router,private userService:UserService,private toast:NgToastService) { }


  forgotPasswordForm: FormGroup=new FormGroup({
    email:new FormControl('',Validators.required),
   

  })
  ngOnInit(): void {
  }
  onSubmit(forgotPasswordForm:any) {
    console.log(forgotPasswordForm.value);
    this.otp.to=forgotPasswordForm.value.email;
    this.userService.userForgot(this.otp).subscribe(
      (response) => {
        console.log(response);
    
        this.router.navigate(['/resetpasswordwithotp']);   
         },
      (error) => {
       
        this.toast.success({detail:"success message",summary:"File has been downloaded",duration:5000})
        this.router.navigate(['/resetpasswordwithotp']);   
         }
    );
  }


}
