import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PreorderService {

 

  private  baseUrl = "http://localhost:8080";

preOrderId: any;
  pre: any;
 
setId(id:any)
{
  this.preOrderId=id;
}
getId()
{
  return this.preOrderId;
}

setPre(pre:any){
  this.pre=pre;
  // console.log(this.items);
  
}
getPre(){
  return this.pre;
}



constructor(private http:HttpClient,private router:Router) { }
header=new HttpHeaders()

savePreOrderDetailsArray(param:any): Observable<any>  
{
  console.log("+++++++++++",param)
  let token=localStorage.getItem('token')
  this.header=new HttpHeaders({
    'Content-Type':'application/json',
    'Authorization':'Supplychain '+token
  })
  return this.http.post(this.baseUrl+'/preorder/array',param,{headers:this.header});
   
}

savePreOrderDetails(param:any): Observable<any>  
{
  console.log("+++++++++++",param)
  let token=localStorage.getItem('token')
  this.header=new HttpHeaders({
    'Content-Type':'application/json',
    'Authorization':'Supplychain '+token
  })
  return this.http.post(this.baseUrl+'/preorder',param,{headers:this.header});
   
}

savePreOrderDetailsItems(param:any): Observable<any>  
{
  console.log(param)
  let token=localStorage.getItem('token')
  this.header=new HttpHeaders({
    'Content-Type':'application/json',
    'Authorization':'Supplychain '+token
  })
  return this.http.post(this.baseUrl+'/preorder/additem',param,{headers:this.header});
   
}

getPreOrderDetails(): Observable<any>  
{
  
  let token=localStorage.getItem('token')
  this.header=new HttpHeaders({
    'Content-Type':'application/json',
    'Authorization':'Supplychain '+token
  })
  console.log("*******88*********");
  
  return this.http.get(this.baseUrl+'/preorder',{headers:this.header});
   
}


preorderPagination(q:any,q1:any)
{ let token=localStorage.getItem('token')
console.log(token)
this.header=new HttpHeaders({
  'Content-Type':'application/json',
  'Authorization' : 'Supplychain '+token})
  return  this.http.get(this.baseUrl+'/preorder/listallpreorder/'+q+'/'+q1,{headers:this.header});


}



deletePreOrderDetails(param:any): Observable<any>  
{
  
  let token=localStorage.getItem('token')
  this.header=new HttpHeaders({
    'Content-Type':'application/json',
    'Authorization':'Supplychain '+token
  })
  return this.http.delete(this.baseUrl+'/preorder/'+param,{headers:this.header});
   
}
updatePreOrderDetails(param:any,id:any): Observable<any>  
{
  console.log("&&&&&&&&&&",param.dPersonId)
  
  let token=localStorage.getItem('token')
  this.header=new HttpHeaders({
    'Content-Type':'application/json',
    'Authorization':'Supplychain '+token
  })
  console.log("foooor",id);
  
  return this.http.put(this.baseUrl+'/preorder/'+id,param,{headers:this.header});
   
}
getPreOrderDetailsbyid(id:any): Observable<any>  
{
  
  let token=localStorage.getItem('token')
  this.header=new HttpHeaders({
    'Content-Type':'application/json',
    'Authorization':'Supplychain '+token
  })
  console.log(id);
  
  return this.http.get(this.baseUrl+'/preorder/'+id,{headers:this.header});
   
}


getItembyid(id:any): Observable<any>  
{
  
  let token=localStorage.getItem('token')
  this.header=new HttpHeaders({
    'Content-Type':'application/json',
    'Authorization':'Supplychain '+token
  })
  return this.http.get(this.baseUrl+'/preorder/getitem/'+id,{headers:this.header});
   
}





changepasswordPurchaser(param:any): Observable<any>  
{
  
  let token=localStorage.getItem('token')
  this.header=new HttpHeaders({
    'Content-Type':'application/json',
    'Authorization':'Supplychain '+token
  })
  return this.http.put(this.baseUrl+'/users/changepassword',param,{headers:this.header});

}



}

