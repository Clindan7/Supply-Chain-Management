import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { SupplierDetails } from '../classes/supplier-details';

@Injectable({
  providedIn: 'root'
})
export class SupplierService {
  deleteSupplier(supplierId: number) {
    throw new Error('Method not implemented.');
  }
  updateSupplier(supplierDetails: SupplierDetails) {
    throw new Error('Method not implemented.');
  }
  supplierId: any;
   
  setId(id:any)
  {
    this.supplierId=id;
  }
  getId()
  {
    return this.supplierId;
  }



  getIdSupplier()
  {
    
  }

  private  baseUrl = "http://localhost:8080";
  constructor(private http:HttpClient,private router:Router) { }
  header=new HttpHeaders()
  
  saveSupplierDetails(param:any): Observable<any>  
  {
   
    return this.http.post(this.baseUrl+'/supplier',param);
     
  }

  getSupplierDetails(): Observable<any>  
  {
    
    let token=localStorage.getItem('token')
    this.header=new HttpHeaders({
      'Content-Type':'application/json',
      'Authorization':'Supplychain '+token
    })
    return this.http.get(this.baseUrl+'/supplier',{headers:this.header});
     
  }


  getCurrentSupplier():Observable<any>
  {
        
    let token=localStorage.getItem('token')
    this.header=new HttpHeaders({
      'Content-Type':'application/json',
      'Authorization':'Supplychain '+token
    })
    return this.http.get(this.baseUrl+'/supplier/current',{headers:this.header});
  }

  

  deleteSupplierDetails(param:any): Observable<any>  
  {
    
    let token=localStorage.getItem('token')
    this.header=new HttpHeaders({
      'Content-Type':'application/json',
      'Authorization':'Supplychain '+token
    })
    return this.http.delete(this.baseUrl+'/supplier/'+param,{headers:this.header});
     
  }
  // updateSupplierDetails(param:any,id:any): Observable<any>  
  // {
    
  //   let token=localStorage.getItem('token')
  //   this.header=new HttpHeaders({
  //     'Content-Type':'application/json',
  //     'Authorization':'Supplychain '+token
  //   })
  //   console.log(param)
  //   return this.http.put(this.baseUrl+'/supplier/'+id,param,{headers:this.header});
     
  // }

  updateSupplierDetails(param:any,id:any): Observable<any> {
    let token=localStorage.getItem('token')
    this.header=new HttpHeaders({
      'Content-Type':'application/json',
      'Authorization':'Supplychain '+token
    })
    console.log(param)
    return this.http.put(this.baseUrl+'/supplier/'+id,param,{headers:this.header});
  }

  
  getSupplierDetailsbyid(id:any): Observable<any>  
  {
    
    let token=localStorage.getItem('token')
    this.header=new HttpHeaders({
      'Content-Type':'application/json',
      'Authorization':'Supplychain '+token
    })


    console.log("new id",id);
    
    return this.http.get(this.baseUrl+'/supplier/'+id,{headers:this.header});
     
  }



  getSuppliers(): Observable<any>  
  {
    
    let token=localStorage.getItem('token')
    this.header=new HttpHeaders({
      'Content-Type':'application/json',
      'Authorization':'Supplychain '+token
    })    
    return this.http.get(this.baseUrl+'/supplier/currentsup',{headers:this.header});
     
  }


  


  changepasswordSupplier(param:any): Observable<any>  
{
  
  let token=localStorage.getItem('token')
  this.header=new HttpHeaders({
    'Content-Type':'application/json',
    'Authorization':'Supplychain '+token
  })
  return this.http.put(this.baseUrl+'/users/changepassword',param,{headers:this.header});

}


supplierImageUpload(param1:any,id:number):Observable<any>{

  const formData: FormData = new FormData();
  formData.append('photos', param1);
  
console.log("****************",id);

  return this.http.post(this.baseUrl+'/supplier/save/image/'+id,formData);
}
}
