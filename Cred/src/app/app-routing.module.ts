import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { LoginComponent } from './component/login/login.component';
import { SignupComponent } from './component/signup/signup.component';
import { ItemMainComponent } from './item-main/item-main.component';
import { FooterComponent } from './component/footer/footer.component';

import { AdminComponent } from './component/admin/admin.component';

import { SupplierComponent } from './component/supplier/supplier.component';
import { SupplierlistComponent } from './component/supplierlist/supplierlist.component';
import { PurchaserComponent } from './component/purchaser/purchaser.component';
import { ViewpurchaserComponent } from './component/viewpurchaser/viewpurchaser.component';
import { SupplierMainComponent } from './component/supplier-main/supplier-main.component';
import { ViewpostComponent } from './component/viewpost/viewpost.component';
import { Supplierlist2Component } from './component/supplierlist2/supplierlist2.component';
import { ProfileComponent } from './component/profile/profile.component';
import { Supplierlist3Component } from './component/supplierlist3/supplierlist3.component';
import { ItemlistPurchaserComponent } from './component/itemlist-purchaser/itemlist-purchaser.component';
import { Viewpurchaser2Component } from './component/viewpurchaser2/viewpurchaser2.component';
import { DeliveryPersonComponent } from './component/delivery-person/delivery-person.component';
import { DeliveryOrderComponent } from './component/delivery-order/delivery-order.component';
import { StatusComponent } from './component/status/status.component';
import { BulkUpdateComponent } from './component/bulk-update/bulk-update.component';
import { FrontPageComponent } from './component/front-page/front-page.component';
import { FrontpageSupplierComponent } from './component/frontpage-supplier/frontpage-supplier.component';
import { PurchaserOrderlistComponent } from './component/purchaser-orderlist/purchaser-orderlist.component';
import { ChangepasswordComponent } from './component/changepassword/changepassword.component';
import { ChangeSupplierpasswordComponent } from './component/change-supplierpassword/change-supplierpassword.component';
import { ChangeDeliverypasswordComponent } from './component/change-deliverypassword/change-deliverypassword.component';
import { ChangePurchaserpasswordComponent } from './component/change-purchaserpassword/change-purchaserpassword.component';
import { ProfileUpdateComponent } from './component/profile-update/profile-update.component';
import { ChatComponent } from './component/chat/chat.component';
import { ChatSupplierComponent } from './component/chat-supplier/chat-supplier.component';
import { ForgotPasswordComponent } from './component/forgot-password/forgot-password.component';
import { ResetPasswordWithOtpComponent } from './component/reset-password-with-otp/reset-password-with-otp.component';
import { SupplierProfileComponent } from './component/supplier-profile/supplier-profile.component';
import { PurchaserProfileComponent } from './component/purchaser-profile/purchaser-profile.component';
import { DeliveryProfileComponent } from './component/delivery-profile/delivery-profile.component';
import { AdminProfileComponent } from './component/admin-profile/admin-profile.component';
import { CalenderComponent } from './component/calender/calender.component';
import { SupplierProfilepicComponent } from './component/supplier-profilepic/supplier-profilepic.component';
// import { OrderrequestComponent } from './component/orderrequest/orderrequest.component';






const routes: Routes = [
  {path:"login", component:LoginComponent},
  {path:"",redirectTo:"login", pathMatch:'full'},
  {path:"login/signup", component:SignupComponent},
  {path:"signup", component:SignupComponent},

  {path:"items",component:ItemMainComponent},
  {path:"footer",component:FooterComponent},
  {path:"admin",component:AdminComponent},


{path:"supplier",component:SupplierComponent},
{path:"supplierlist",component:SupplierlistComponent},
{path:"purchaser",component:PurchaserComponent},
{path:"purchaser",component:PurchaserComponent},
{path:"viewpurchaser", component:ViewpurchaserComponent},
{path:"supplier-main", component:SupplierMainComponent}
,{path:"viewpost",component:ViewpostComponent},
{path:"supplierlist2",component:Supplierlist2Component},
{path:"profile",component:ProfileComponent},
{path:"supplierlist3",component:Supplierlist3Component},
{path:"itemlist-purchaser",component:ItemlistPurchaserComponent},
{path:"viewpurchaser2",component:Viewpurchaser2Component},
{path:"delivery",component:DeliveryPersonComponent},
{path:"delivery-order",component:DeliveryOrderComponent},
{path:"status",component:StatusComponent},
{path:"bulk-update",component:BulkUpdateComponent},
{path:"front-page",component:FrontPageComponent},
{path:"frontpage-supplier",component:FrontpageSupplierComponent},
{path:"order-list",component:PurchaserOrderlistComponent},
{path:"change-password",component:ChangepasswordComponent},
{path:"change-supplierpassword",component:ChangeSupplierpasswordComponent},
{path:"change-deliverypassword",component:ChangeDeliverypasswordComponent},
{path:"change-purchaserpassword",component:ChangePurchaserpasswordComponent},
{path:"profile-update",component:ProfileUpdateComponent},
{path:"chat",component:ChatComponent},
{path:"chat-supplier",component:ChatSupplierComponent},
{path:"forgot",component:ForgotPasswordComponent},
{path:"resetpasswordwithotp",component:ResetPasswordWithOtpComponent},
{path:"supplier-profile",component:SupplierProfileComponent},
{path:"purchaser-profile",component:PurchaserProfileComponent},
{path:"delivery-profile",component:DeliveryProfileComponent},
{path:"admin-profile",component:AdminProfileComponent},
{path:"calender",component:CalenderComponent},
{path:"supplier-profilepic",component:SupplierProfilepicComponent}



];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
