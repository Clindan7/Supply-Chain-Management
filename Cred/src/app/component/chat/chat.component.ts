
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ChatServiceService } from 'src/app/services/chat-service.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {

  data1: any;
  id: any;
  searchText:any;
  user: any;
  senderId: any;
  name: any;
  userprofile: any;
  data: any;
  val:any="active";
  searchData: any;
  senders: any;
  userextra: any;
  chats: any;
  receiver: any;
  users:any;
  test:any;
  names:any;
  name1:any;
  constructor(private router:Router,private cht:ChatServiceService,
    private userService:UserService) { }

  
  searchForm: FormGroup = new FormGroup({

  })

  chatForm: FormGroup= new FormGroup({
    message:new FormControl('',[Validators.maxLength(250),Validators.required]),
    senderId:new FormControl('',[Validators.required]),
    receiverId:new FormControl('',[Validators.required]),

  })

  ngOnInit(): void {
  
    this.cht.LoadSupplier().subscribe(data=>{
      this.users=data
      console.log(this.users);
      
    })
    
    this.cht.loadcuruser().subscribe(user=>{
      this.user=user;
      console.log(user)
      this.senderId=this.user.userId;

    })

}

relod(data:any){
  this.cht.LoadChat(data.userId).subscribe(result=>{
    this.data1=result
    console.log("here",this.data1);
    

  })
}

call(data:any){
  // this.names=data;
  console.log("anuuuuuuuuuuu",data.userId);
  this.id=data.userId;
  console.log("datattatat",this.id);

this.userService.getUserDetailsbyid(this.id).subscribe(
response =>
{
  this.userprofile=response;
  console.log("haii",this.userprofile);
  console.log("jo",this.userprofile.name);
  this.name1=this.userprofile.name;
}
)

this.userService.getCurrentUser().subscribe(
  response =>
  {
    
    this.user=response
    console.log(this.user)
 
 
  }
);
  
    this.cht.LoadChat(data.userId).subscribe(result=>{
      this.data1=result
      console.log("here",this.data1);
      

    })
    
}

chatmsg(){

  this.chatForm.value.senderId=this.senderId;
  console.log("llfllfl",this.id);
  this.chatForm.value.receiverId=this.id;

  console.log("fsdfdsfdfsdd",this.chatForm.value.receiverId);
  console.log("yeses",this.chatForm.value)

  this.cht.sendchat(this.chatForm.value).subscribe(result=>{
    console.log(result)
    this.cht.LoadChat(this.id).subscribe(result=>{
      this.data1=result
      console.log("here",this.data1);
      

    }) 
    
   
this.chatForm.reset()
  })

}

logout(){
   
  localStorage.removeItem('accessToken');
  localStorage.removeItem('refreshToken');
  this.router.navigate(["/"])
}

}