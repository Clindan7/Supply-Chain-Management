import { Injectable } from '@angular/core';
import{ HttpClient, HttpHeaders} from '@angular/common/http';
import { from, Observable } from 'rxjs';  
import { Router } from '@angular/router';  
import { OtpDetails } from '../classes/otp-details';

@Injectable({
  providedIn: 'root'
})
export class UserService {
 

  userId: any;
   
  setId(id:any)
  {
    this.userId=id;
  }

  
  getId()
  {
    return this.userId;
  }
  corsHeaders:HttpHeaders = new HttpHeaders({
    'Content-Type': 'application/json',
  });
    private  baseUrl = "http://localhost:8080";
    header=new HttpHeaders()
  constructor(private http:HttpClient,private router:Router) { }

  login(param :any): Observable<any> {

    return this.http.post(this.baseUrl+'/login',param);
  }
  saveUserDetails(param:any): Observable<any>  
  {
    return  this.http.post(this.baseUrl+'/users',param);
     
  }
  getUserDetailsbyid(id:any): Observable<any>  
  {
    console.log("*******",id)
    return this.http.get(this.baseUrl+'/users/'+id);
     
  }


  getLoggedUserDetails(id:any): Observable<any>
  {

    console.log("iiiiiiiiiiddddddddd",id);
    
    return this.http.get(this.baseUrl+'/users/loggeduser/'+id);
    
  }



  getCurrentUser(): Observable<any>  
  {
    
    let token=localStorage.getItem('token')
    this.header=new HttpHeaders({
      'Content-Type':'application/json',
      'Authorization':'Supplychain '+token
    })
    return this.http.get(this.baseUrl+'/login',{headers:this.header});
     
  }



  public userVerify(otp:OtpDetails){
    console.log("-------------------------------------------",otp);
    let token=localStorage.getItem('token')
    this.header=new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization':'Supplychain '+token
    })
    return this.http.post("http://localhost:8080/verifyotp",otp,{headers:this.header});
  }
  public userForgot(val:OtpDetails){
    console.log("+++++++++++++++++++++++++++++++++",val);
    
    this.header=new HttpHeaders({
      'Content-Type': 'application/json'
    })
    return this.http.post("http://localhost:8080/emailsent",val,{headers:this.header});
  }

}

