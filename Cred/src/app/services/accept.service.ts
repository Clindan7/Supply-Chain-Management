import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AcceptService {
  private  baseUrl = "http://localhost:8080";

preOrderId: any;
postOrderId:any;

setId(id:any)
{
  this.preOrderId=id;
}
getId()
{
  return this.preOrderId;
}

setIdz(id:any)
{
  this.postOrderId=id;
}
getIdz()
{
  return this.postOrderId;
}

constructor(private http:HttpClient,private router:Router) { }
header=new HttpHeaders()



  saveAcceptDetails(param:any) : Observable<any>   {
    console.log(param,"servvv")
    let token=localStorage.getItem('token')
    this.header=new HttpHeaders({
      'Content-Type':'application/json',
      'Authorization':'Supplychain '+token
    })
    return this.http.post(this.baseUrl+'/postorder',param,{headers:this.header});
  }


  getPostDetailsStatus(id:number):Observable<any>  
  {
        
    let token=localStorage.getItem('token')
    this.header=new HttpHeaders({
      'Content-Type':'application/json',
      'Authorization':'Supplychain '+token
    })
    console.log(")))))))))))",id);
    
return this.http.get(this.baseUrl+'/postorder/status/'+id,{headers:this.header});
  }

  getPostDetails(): Observable<any>  
  {
    
    let token=localStorage.getItem('token')
    this.header=new HttpHeaders({
      'Content-Type':'application/json',
      'Authorization':'Supplychain '+token
    })
    return this.http.get(this.baseUrl+'/postorder',{headers:this.header});
     
  }

  getPostDetailsByDate(): Observable<any>  
  {
    
    let token=localStorage.getItem('token')
    this.header=new HttpHeaders({
      'Content-Type':'application/json',
      'Authorization':'Supplychain '+token
    })
    return this.http.get(this.baseUrl+'/postorder/listbydate',{headers:this.header});
     
  }

  

  getPostDetailsbyid(id:any): Observable<any>  
{
  
  let token=localStorage.getItem('token')
  this.header=new HttpHeaders({
    'Content-Type':'application/json',
    'Authorization':'Supplychain '+token
  })
  return this.http.get(this.baseUrl+'/postorder/'+id,{headers:this.header});
   
}


getPostById(): Observable<any>  
{
  
  let token=localStorage.getItem('token')
  this.header=new HttpHeaders({
    'Content-Type':'application/json',
    'Authorization':'Supplychain '+token
  })
  return this.http.get(this.baseUrl+'/postorder/postorderbyid',{headers:this.header});
   
}


getPostOrders(id:number){
  let token=localStorage.getItem('token')
  this.header=new HttpHeaders({
    'Content-Type':'application/json',
    'Authorization':'Supplychain '+token
  })
  return this.http.get(this.baseUrl+'/postorder/getpostorder/'+id,{headers:this.header});

}

getPostOrdersByQuery(itemId:any){
  let token=localStorage.getItem('token')
  this.header=new HttpHeaders({
    'Content-Type':'application/json',
    'Authorization':'Supplychain '+token
  })
  return this.http.get(this.baseUrl+'/postorder/getpostorderbyquery/'+itemId,{headers:this.header});

}



getAcceptRequest(id:number){

  let token=localStorage.getItem('token')
  this.header=new HttpHeaders({
    'Content-Type':'application/json',
    'Authorization':'Supplychain '+token
  })
 
return this.http.put(this.baseUrl+'/acceptrequest/'+id,{},{headers:this.header});
}


getAcceptRequestDelivery(id:number){

  
  return this.http.post(this.baseUrl+'/acceptrequestdelivery/'+id,{headers:this.header});
  }


getInvoice(id:number){

  
  return this.http.post(this.baseUrl+'/invoice/'+id,{headers:this.header});
  }
  

  getShipped(id:number){
      
    return this.http.post(this.baseUrl+'/shipped/'+id,{headers:this.header});
    }

    getShippedBulk(){
      
      return this.http.post(this.baseUrl+'/shipped',{headers:this.header});
      }


      getBulkShip(){

  return this.http.get(this.baseUrl+'/postorder/bulkship/orders');

      }
  
      getBulkDeli(){
  
        return this.http.get(this.baseUrl+'/postorder/bulkdelivery/orders');
      
            }
        



      getDeliveredBulk(){
              
      return this.http.post(this.baseUrl+'/delivered',{headers:this.header});
      }


  getDelivered(id:number){
    return this.http.post(this.baseUrl+'/delivered/'+id,{headers:this.header});
  }

deleteRejectRequest(id:number){
  let token=localStorage.getItem('token')
  this.header=new HttpHeaders({
    'Content-Type':'application/json',
    'Authorization':'Supplychain '+token
  })
  return this.http.delete(this.baseUrl+'/postorder/deleterequest/'+id,{headers:this.header});
}

displayCsv():Observable<Object>{
  console.log(localStorage.getItem('token'))
  return this.http.get(this.baseUrl+'/api/csv/postordercsv',{observe:'response',responseType:'blob',headers:new HttpHeaders({'Authorization':'Supplychain '+localStorage.getItem('token')})})
}


displayPreorderCsv():Observable<Object>{
  console.log(localStorage.getItem('token'))
  return this.http.get(this.baseUrl+'/api/csv/preordercsv',{observe:'response',responseType:'blob',headers:new HttpHeaders({'Authorization':'Supplychain '+localStorage.getItem('token')})})
}





changepassword(param:any): Observable<any>  
{
  
  let token=localStorage.getItem('token')
  this.header=new HttpHeaders({
    'Content-Type':'application/json',
    'Authorization':'Supplychain '+token
  })
  return this.http.put(this.baseUrl+'/users/changepassword',param,{headers:this.header});

}


postorderPagination(p:any, p1:any){

  let token=localStorage.getItem('token')
  console.log(token)
  this.header=new HttpHeaders({
    'Content-Type':'application/json',
    'Authorization' : 'Supplychain '+token
  })
  return  this.http.get(this.baseUrl+'/postorder/listallpostorder/'+p+'/'+p1,{headers:this.header});

}





}