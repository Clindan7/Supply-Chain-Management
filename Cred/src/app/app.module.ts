import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './component/login/login.component';
import { SignupComponent } from './component/signup/signup.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { ItemMainComponent } from './item-main/item-main.component';

import { FooterComponent } from './component/footer/footer.component';
import { AdminComponent } from './component/admin/admin.component';

import { SupplierComponent } from './component/supplier/supplier.component';
import { SupplierlistComponent } from './component/supplierlist/supplierlist.component';
import { PurchaserComponent } from './component/purchaser/purchaser.component';
import { ViewpurchaserComponent } from './component/viewpurchaser/viewpurchaser.component';
import { SupplierMainComponent } from './component/supplier-main/supplier-main.component';
import { ViewpostComponent } from './component/viewpost/viewpost.component';
import { NgToastModule } from 'ng-angular-popup';
import { Supplierlist2Component } from './component/supplierlist2/supplierlist2.component';
import { ProfileComponent } from './component/profile/profile.component';
import { Supplierlist3Component } from './component/supplierlist3/supplierlist3.component';

import { ItemlistPurchaserComponent } from './component/itemlist-purchaser/itemlist-purchaser.component';
import { Viewpurchaser2Component } from './component/viewpurchaser2/viewpurchaser2.component';
import { NgMultiSelectDropDownModule } from 'ng-multiselect-dropdown';
import { DeliveryPersonComponent } from './component/delivery-person/delivery-person.component';
import { DeliveryOrderComponent } from './component/delivery-order/delivery-order.component';
import { StatusComponent } from './component/status/status.component';
import { BulkUpdateComponent } from './component/bulk-update/bulk-update.component';
import { NgxPaginationModule } from 'ngx-pagination';
import { FrontpageSupplierComponent } from './component/frontpage-supplier/frontpage-supplier.component';
import { Header2Component } from './component/header2/header2.component';
// import {  MatDialog, MatDialogContent, MatDialogModule,MatPaginatorModule } from '@angular/material';

import { MatButtonModule } from '@angular/material/button';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { PurchaserOrderlistComponent } from './component/purchaser-orderlist/purchaser-orderlist.component';
import { Ng2SearchPipe, Ng2SearchPipeModule } from 'ng2-search-filter';
import { ChangepasswordComponent } from './component/changepassword/changepassword.component';
import { ChangeSupplierpasswordComponent } from './component/change-supplierpassword/change-supplierpassword.component';
import { ChangeDeliverypasswordComponent } from './component/change-deliverypassword/change-deliverypassword.component';
import { ChangePurchaserpasswordComponent } from './component/change-purchaserpassword/change-purchaserpassword.component';
import { ProfileUpdateComponent } from './component/profile-update/profile-update.component';
import { ChatComponent } from './component/chat/chat.component';
import { ChatSupplierComponent } from './component/chat-supplier/chat-supplier.component';
import { ForgotPasswordComponent } from './component/forgot-password/forgot-password.component';
import { ResetPasswordWithOtpComponent } from './component/reset-password-with-otp/reset-password-with-otp.component';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MatDialogModule } from '@angular/material/dialog';
import { SupplierProfileComponent } from './component/supplier-profile/supplier-profile.component';
import { PurchaserProfileComponent } from './component/purchaser-profile/purchaser-profile.component';
import { DeliveryProfileComponent } from './component/delivery-profile/delivery-profile.component';
import { AdminProfileComponent } from './component/admin-profile/admin-profile.component';
import { FullCalendarModule } from '@fullcalendar/angular';
import { CalenderComponent } from './component/calender/calender.component';
import { SupplierProfilepicComponent } from './component/supplier-profilepic/supplier-profilepic.component';







@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SignupComponent,
    ItemMainComponent,
    FooterComponent,
    AdminComponent,
    PurchaserOrderlistComponent,
SupplierComponent,
SupplierlistComponent,
PurchaserComponent,
ViewpurchaserComponent,
SupplierMainComponent,
ViewpostComponent,
Supplierlist2Component,
ProfileComponent,
Supplierlist3Component,

ItemlistPurchaserComponent,
Viewpurchaser2Component,
DeliveryPersonComponent,
DeliveryOrderComponent,
StatusComponent,
BulkUpdateComponent
, FrontpageSupplierComponent,
 Header2Component,
  ChangepasswordComponent,
   ChangeSupplierpasswordComponent, 
ChangeDeliverypasswordComponent,
 ChangePurchaserpasswordComponent,
  ProfileUpdateComponent,
   ChatComponent, 
   ChatSupplierComponent,
    ForgotPasswordComponent, 
    ResetPasswordWithOtpComponent,
     SupplierProfileComponent,
      PurchaserProfileComponent,
       DeliveryProfileComponent,
        AdminProfileComponent,
        CalenderComponent,
        SupplierProfilepicComponent,
         





  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    NgxPaginationModule,

    HttpClientModule,
    MatButtonModule,
    BrowserAnimationsModule,

    NgToastModule,
    NgMultiSelectDropDownModule.forRoot(),
    Ng2SearchPipeModule,
    MatPaginatorModule,
    MatDialogModule,
    FullCalendarModule


    ],


  providers: [ ],
  bootstrap: [AppComponent]
})
export class AppModule { }  
