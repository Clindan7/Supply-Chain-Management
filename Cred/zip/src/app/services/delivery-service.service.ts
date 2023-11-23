
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';


import { Router } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DeliveryServiceService {
  dPersonId: any;
   
  setId(id:any)
  {
    this.dPersonId=id;
  }
  getId()
  {
    return this.dPersonId;
  }

  private  baseUrl = "http://localhost:8080";
  
  constructor(private http:HttpClient,private router:Router) { }
  header=new HttpHeaders()

  
  saveDeliveryDetails(param:any): Observable<any>  
  {

    return this.http.post(this.baseUrl+'/delivery',param);
     
  }

  getDeliveryDetails(): Observable<any>  
  {
    
    let token=localStorage.getItem('token')
    this.header=new HttpHeaders({
      'Content-Type':'application/json',
      'Authorization':'Supplychain '+token
    })
    return this.http.get(this.baseUrl+'/delivery',{headers:this.header});
     
  }

  deleteDeliveryDetails(param:any): Observable<any>  
  {
    
    let token=localStorage.getItem('token')
    this.header=new HttpHeaders({
      'Content-Type':'application/json',
      'Authorization':'Supplychain '+token
    })
    return this.http.delete(this.baseUrl+'/delivery/'+param,{headers:this.header});
     
  }

  updateDeliveryDetails(param:any,id:any): Observable<any> {
    let token=localStorage.getItem('token')
    this.header=new HttpHeaders({
      'Content-Type':'application/json',
      'Authorization':'Supplychain '+token
    })
    console.log(param)
    return this.http.put(this.baseUrl+'/delivery/'+id,param,{headers:this.header});
  }

  
  changepasswordDelivery(param:any): Observable<any>  
{
  
  let token=localStorage.getItem('token')
  this.header=new HttpHeaders({
    'Content-Type':'application/json',
    'Authorization':'Supplychain '+token
  })
  return this.http.put(this.baseUrl+'/users/changepassword',param,{headers:this.header});

}




}