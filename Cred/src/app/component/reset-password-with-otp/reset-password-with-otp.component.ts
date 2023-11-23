import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserDetails } from 'src/app/classes/user-details';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-reset-password-with-otp',
  templateUrl: './reset-password-with-otp.component.html',
  styleUrls: ['./reset-password-with-otp.component.css']
})
export class ResetPasswordWithOtpComponent implements OnInit {

  user=new UserDetails();

  public verifyOtpForm:FormGroup|any;

  constructor(private router:Router,private userService:UserService) {

  this.verifyOtpForm=new FormGroup({
    otp:new FormControl('',[Validators.required]),
    newPassword:new FormControl('',[Validators.required,Validators.pattern('^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{8,12}$')]),
    cnewPassword:new FormControl('',[Validators.required,Validators.pattern('^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{8,12}$')])

})
}

  ngOnInit(): void {
  }
  
  onSubmit(){
    console.log("++++++++))))))))))))))))))+++++++++++++++",this.verifyOtpForm.value);

    //this.user.lastName=this.verifyOtpForm.value.lastName
    this.userService.userVerify(this.verifyOtpForm.value).subscribe(
      (response) => {
        console.log("Response$$$$$$$$$$$$$$$$$$$$$$",response);
        alert("Password Updated!!")
      })

        this.router.navigate(['/login']);
  }
}
