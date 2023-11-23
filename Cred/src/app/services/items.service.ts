import { Injectable } from '@angular/core';
import{ HttpClient,HttpEvent,HttpHeaders, HttpRequest} from '@angular/common/http';
import { Observable } from 'rxjs';  
import { Router } from '@angular/router';  
import { ItemDetails } from '../classes/item-details';


@Injectable({
  providedIn: 'root'
})
export class ItemsService {

  itemId: any;
  id: any;
  items:any; 
  arr:any;
  fileName: any;
  movies:any;

  setMovies(movies:any){
    this.movies=movies;
  }

  getMovies(){
    return this.movies;
  }
  
  setId(id:any)
  {
    this.itemId=id;
 
  }
  getId()
  {
    return this.itemId;
  }
  

  setItems(items:any){
    this.items=items;
    // console.log(this.items);
    
  }
  getItems(){
    return this.items;
  }
  
  private  baseUrl = "http://localhost:8080";
  constructor(private http:HttpClient,private router:Router){}
header=new HttpHeaders()
  saveItemDetails(param:any): Observable<any>  
  {
    console.log(param)
    let token=localStorage.getItem('token')
    this.header=new HttpHeaders({
      'Content-Type':'application/json',
      'Authorization':'Supplychain '+token
    })
    return this.http.post(this.baseUrl+'/items',param,{headers:this.header});
     
  }


  getItemDetails(): Observable<any>  
  {
    
    let token=localStorage.getItem('token')
    this.header=new HttpHeaders({
      'Content-Type':'application/json',
      'Authorization':'Supplychain '+token
    })
    return this.http.get(this.baseUrl+'/items/listall',{headers:this.header});
     
  }


  getItemDetailsbyPost(): Observable<any>{
    let token=localStorage.getItem('token')
    this.header=new HttpHeaders({
      'Content-Type':'application/json',
      'Authorization':'Supplychain '+token
    })
    return this.http.get(this.baseUrl+'/items/listbypost',{headers:this.header});

  }


  enrolledPagination(p:any, p1:any){

    let token=localStorage.getItem('token')
    console.log(token)
    this.header=new HttpHeaders({
      'Content-Type':'application/json',
      'Authorization' : 'Contacts '+token
    })
    return  this.http.get(this.baseUrl+'/items/listall'+p+'/'+p1,{headers:this.header});

  }


  getItemBySupplier(): Observable<any>  
  {
    
    let token=localStorage.getItem('token')
    this.header=new HttpHeaders({
      'Content-Type':'application/json',
      'Authorization':'Supplychain '+token
    })
    return this.http.get(this.baseUrl+'/items/listallbysupplier',{headers:this.header});
     
  }


  deleteItemDetails(param:any): Observable<any>  
  {
    
    let token=localStorage.getItem('token')
    this.header=new HttpHeaders({
      'Content-Type':'application/json',
      'Authorization':'Supplychain '+token
    })
    return this.http.delete(this.baseUrl+'/items/'+param,{headers:this.header});
     
  }
  updateItemDetails(param:any,id:any): Observable<any>  
  {
    
    let token=localStorage.getItem('token')
    this.header=new HttpHeaders({
      'Content-Type':'application/json',
      'Authorization':'Supplychain '+token
    })
    return this.http.put(this.baseUrl+'/items/'+id,param,{headers:this.header});
     
  }

  getItemDetailsbyid(id:any): Observable<any>  
  {
    
    let token=localStorage.getItem('token')
    this.header=new HttpHeaders({
      'Content-Type':'application/json',
      'Authorization':'Supplychain '+token
    })
    return this.http.get(this.baseUrl+'/items/'+id,{headers:this.header});
     
  }







  getItemDetailsbyArrayid(id:any): Observable<any>  
  {
    console.log("kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk",id);
    
    let token=localStorage.getItem('token')
    this.header=new HttpHeaders({
      'Content-Type':'application/json',
      'Authorization':'Supplychain '+token
    })
    return this.http.get(this.baseUrl+'/items/item/'+id,{headers:this.header});
     
  }



 
 

  getProfile() {
    let token=localStorage.getItem('token')
    this.header=new HttpHeaders({
    
      responseType: "blob", observe: "response" ,
     

      'Authorization':'Supplychain '+token
    })
    return this.http.get(this.baseUrl+"/items/profile",{headers:this.header});
  
  }


  ItemImageUpload(param1:any,id:number):Observable<any>{

    const formData: FormData = new FormData();
    formData.append('photos', param1);

    return this.http.post(this.baseUrl+'/items/save/image/'+id,formData);
  }

  
  ItemImageUpdate(param1:any,id:number):Observable<any>{

    const formData: FormData = new FormData();
    formData.append('photos', param1);

    return this.http.put(this.baseUrl+'/items/update/image/'+id,formData);
  }


  


  onFileSelected(event:any) {

    const file:File = event.target.files[0];
  
    if (file) {
  
        this.fileName = file.name;
  
        const formData = new FormData();
  
        formData.append("thumbnail", file);
  
        const upload$ = this.http.post("/api/csv", formData);
  
        upload$.subscribe();
    }
  }



  sendCollection(arr:any): Observable<any>  
  {
    
    let token=localStorage.getItem('token')
    this.header=new HttpHeaders({
      'Content-Type':'application/json',
      'Authorization':'Supplychain '+token
    })
    return this.http.post(this.baseUrl+'/items/collection',arr,{headers:this.header});
     
  }

  getitem(name:any,fItem:any):Observable<any>{
    let token=localStorage.getItem('token')
    this.header=new HttpHeaders({
      'Content-Type':'application/json',
      'Authorization':'Supplychain '+token
    })
    if((name === undefined) && (fItem === undefined)){

      return this.http.get(this.baseUrl+`/items/filter`,{headers:this.header});
    }
    else if((name !== undefined) && (fItem === undefined)) {
      return this.http.get(this.baseUrl+`/items/filter?itemName=${name}`,{headers:this.header});
   
    }
    else if((name === undefined) && (fItem !== undefined)) {
      return this.http.get(this.baseUrl+`/items/filter?filterItem=${fItem}`,{headers:this.header});
    }

    else
      return this.http.get(this.baseUrl+`/items/filter?filterItem=${fItem}&&itemName=${name}`,{headers:this.header});
  }


  upload(file: File): Observable<HttpEvent<any>> {
    let token=localStorage.getItem('token')
  this.header=new HttpHeaders(
    {
    
      'Authorization':'Supplychain '+token
    })
    const formData: FormData = new FormData();

    formData.append('file', file);

    const req = new HttpRequest('POST', `${this.baseUrl}/api/csv/upload`, formData, {headers:this.header} );

    return this.http.request(req);
  }



  displayItemCsv():Observable<Object>{
    console.log(localStorage.getItem('token'))
    return this.http.get(this.baseUrl+'/api/csv/download',{observe:'response',responseType:'arraybuffer',headers:new HttpHeaders({'Authorization':'Supplychain '+localStorage.getItem('token')})})
  }

  setBulk(param:any): Observable<any>  
{
  console.log("+++++++++++",param)
  let token=localStorage.getItem('token')
  this.header=new HttpHeaders({
    'Content-Type':'application/json',
    // 'Authorization':'Supplychain '+token
  })
  return this.http.post(this.baseUrl+'/postorder/bulkshipped/'+param,{headers:this.header});
}
 
}




  

