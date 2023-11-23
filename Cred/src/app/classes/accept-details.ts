import { DeliveryDetails } from "./delivery-details";
import { ItemDetails } from "./item-details";
import { SupplierDetails } from "./supplier-details";

export class AcceptDetails {
        preOrderId:any;
        item=new ItemDetails();
        itemId:any;
        quantity:any;
        address:any;
        supplierId:any;
        supplierName:any;
        price:any;
        userId:any;
        supplier=new SupplierDetails();
deliveryPerson=new DeliveryDetails();
        itemName:any;
        description:any;
        type:any;
        photos:any;
        dDate:any;
      
        dPersonName:any;
        dPersonId:any;

        postorderId:any
}
