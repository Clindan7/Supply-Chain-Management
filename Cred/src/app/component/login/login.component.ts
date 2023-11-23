import { Component, OnInit } from '@angular/core';
import { FormControl,FormGroup, Validators} from '@angular/forms';
import { Router } from '@angular/router';
import { UserDetails } from 'src/app/classes/user-details';
import { UserService } from 'src/app/services/user.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  private userDetails=new UserDetails();
  userId:any;
  form: FormGroup=new FormGroup({
    email:new FormControl('',Validators.required),
    password:new FormControl(),
    role:new FormControl()

  })
  
    constructor(private router:Router ,private userService:UserService) { }
  
    ngOnInit(): void {
    }
  loginAction()
  {

  
  this.userService.login(this.form.value).subscribe({
    next:(res:any)=>{
      console.log(res);
      localStorage.setItem('userId',res.userId.value)

      localStorage.setItem('token',res.accessToken.value)
      localStorage.setItem('email',res.email)
      console.log("response recieved");
      this.userId=localStorage.getItem("userId")
      console.log(this.userId)
      
  var tok=localStorage.getItem('token')
  console.log(tok);
  
      if(tok&&res.role==0 )  
      {  
        this.router.navigateByUrl("admin");
      }  
      else if(tok&&res.role==1 )  
      {  
        this.router.navigateByUrl("frontpage-supplier");
      } 
      else if(tok&&res.role==2 )  
      {  
        this.router.navigateByUrl("front-page");
      } 
      else if(tok&&res.role==3)  
      {  
        this.router.navigateByUrl("delivery-order");
      } 
      else  
      {  
          alert("Login failed.")  
      }  
      
    },
    error:(err:any)=>{
      console.log(err);
      
    }
  })
  }
}