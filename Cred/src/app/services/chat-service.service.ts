import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class ChatServiceService {
  store:any;
  header:any;
  
    constructor(private  http:HttpClient) { }
  
    LoadChat(id:any):Observable<any>{
      this.store=localStorage.getItem("token")
      console.log(this.store);
      this.header=new HttpHeaders({
        'Content-Type':'application/json',
        'Authorization':'Supplychain '+ this.store
      })
      return this.http.get('http://localhost:8080/chat/chat/'+id,{headers:this.header})
    }
  
    
    loadcuruser(){
      
     
      this.store=localStorage.getItem("token")
      console.log("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$",this.store);
      console.log(this.store);
      this.header=new HttpHeaders({
        'Content-Type':'application/json',
        'Authorization':'Supplychain '+ this.store
      })
      return this.http.get('http://localhost:8080/login',{headers:this.header})
    }
  
    sendchat(data:any):Observable<any>{
      this.store=localStorage.getItem("token")
      console.log(this.store);
      this.header=new HttpHeaders({
        'Content-Type':'application/json',
        'Authorization':'Supplychain '+ this.store
      })
      return this.http.post('http://localhost:8080/chat',data,{headers:this.header})
    }
  
    LoadAllUser(): Observable<any>  
    {
      this.store=localStorage.getItem("token")
      console.log(this.store);
      this.header=new HttpHeaders({
        'Content-Type':'application/json',
        'Authorization':'Supplychain '+ this.store
      })
      return this.http.get('http://localhost:8080/users',{headers:this.header})
    }

    LoadSupplier(): Observable<any>  
    {
      this.store=localStorage.getItem("token")
      console.log(this.store);
      this.header=new HttpHeaders({
        'Content-Type':'application/json',
        'Authorization':'Supplychain '+ this.store
      })
      return this.http.get('http://localhost:8080/users/supplier/users',{headers:this.header})
    }

    
    LoadPurchaser(): Observable<any>  
    {
      this.store=localStorage.getItem("token")
      console.log(this.store);
      this.header=new HttpHeaders({
        'Content-Type':'application/json',
        'Authorization':'Supplychain '+ this.store
      })
      return this.http.get('http://localhost:8080/users/purchaser/users',{headers:this.header})
    }

  }
  
  