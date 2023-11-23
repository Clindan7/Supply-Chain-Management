package com.supplychain.supplychain.controller;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.supplychain.supplychain.entity.Item;
import com.supplychain.supplychain.entity.Otp;
import com.supplychain.supplychain.entity.PostOrder;
import com.supplychain.supplychain.entity.PreOrder;
import com.supplychain.supplychain.entity.User;
import com.supplychain.supplychain.form.EmailForm;
import com.supplychain.supplychain.repository.OtpRepository;
import com.supplychain.supplychain.repository.PostOrderRepository;
import com.supplychain.supplychain.repository.PreOrderRepository;
import com.supplychain.supplychain.security.util.SecurityUtil;
import com.supplychain.supplychain.service.EmailService;
import com.supplychain.supplychain.service.PostOrderService;
import com.supplychain.supplychain.service.PreOrderService;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.hibernate.dialect.function.StandardAnsiSqlAggregationFunctions.SumFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.FileNotFoundException;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.color.DeviceRgb;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import com.supplychain.supplychain.entity.PostOrder;
import com.supplychain.supplychain.entity.PreOrder;
import com.supplychain.supplychain.entity.User;
import com.supplychain.supplychain.form.PreOrderForm;
import com.supplychain.supplychain.repository.PostOrderRepository;
import com.supplychain.supplychain.repository.UserRepository;
import com.supplychain.supplychain.service.EmailService;
import com.supplychain.supplychain.service.PostOrderService;
import com.supplychain.supplychain.service.PreOrderService;
import com.supplychain.supplychain.view.PreOrderView;

@RestController
public class EmailController {

    @Autowired 
    
    private EmailService emailService;
    @Autowired
    private PostOrderRepository postorderRepository;


    @Autowired
    private PreOrderRepository preOrderRepository ;

    @Autowired
    private PreOrderService preOrderService;

    @Autowired
    private PostOrderService postorderService;


    @Autowired
    private OtpRepository otpRepository;

 
    // @PostMapping("/emailsent")
    // public ResponseEntity<?>sendEmail(@RequestBody EmailForm request)
    // {
    // boolean result = this.emailService.sendEmail("OTP Verification", "hi this is a otp to confirm your password "+"use it to authenticate", request.getTo());
    //      if(result){
    //          return  ResponseEntity.ok("Email Properly Sent Successfully... ");
    //    }else{
    //          return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("email sending failed");
    //      }
    //  }
 
    @PostMapping("/emailsent")
    public ResponseEntity<?> sendEmail(@RequestBody EmailForm request){
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        Otp otp2= new Otp();
        otp2.setOtp(otp);
        otp2.setEmail(request.getTo());
        otpRepository.deleteAll();
        otpRepository.save(otp2);
        boolean result = this.emailService.sendEmail("OTP Verification", "hi this is a otp to confirm your password "+otp+"  .Use it to authenticate", request.getTo());
        if(result){
            return  ResponseEntity.ok("Email Properly Sent Successfully... ");
        }
        else{
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("email sending failed");
        }
    }

 

     @PutMapping("/acceptrequest/{postorderId}")
     public void sendEmail2(@PathVariable ("postorderId") Integer postorderId)
     {
        System.out.println("????????????"+SecurityUtil.getCurrentUserId());

        PostOrder postorder= postorderRepository.findByPostOrderId(postorderId);
        PreOrder preorder=postorder.getPreorder();
        User user=preorder.getUser();
        String to=user.getEmail();
        // PostOrder postorder=postorderRepository.findByPostOrderId(SecurityUtil.getCurrentUserId());
       
        String content="hello";
        String subject="haaai";
        System.out.println(to);
        emailService.sendEmail(subject,content,to);

        Item item=preorder.getItem();
        Integer itemId=item.getItemId();

        postorderService.orderStatusUpdate(postorderId,itemId);





    }

    @PostMapping("/acceptrequestdelivery/{postorderId}")
     public void sendEmail3(@PathVariable ("postorderId") Integer postorderId)
     {
        PostOrder postorder= postorderRepository.findByPostOrderId(postorderId);
        PreOrder preorder=postorder.getPreorder();
        User user=preorder.getUser();
        String to=user.getEmail();
        // PostOrder postorder=postorderRepository.findByPostOrderId(SecurityUtil.getCurrentUserId());
       
        String content="hello";
        String subject="haaai";
        System.out.println(to);
        emailService.sendEmail(subject,content,to);


        // postorderService.orderStatusUpdate1(postorderId);

        postorderService.orderStatusUpdate1(postorderId);


    }




    @PostMapping("/invoice/{postorderId}")
  
    public void generatePdfFile(@PathVariable("postorderId") Integer postorderId) throws MalformedURLException, MessagingException{
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
        String currentDateTime = dateFormat.format(new Date());
        String filename="invoice"+currentDateTime+".pdf";
        String filepath="/home/clindan/Downloads/"+filename;
        PostOrder request=postorderRepository.findByPostOrderId(postorderId);
        try{
        PdfWriter writer=new PdfWriter(filepath);
        PdfDocument pdfdoc=new PdfDocument(writer);
        Document document=new Document (pdfdoc);
        pdfdoc.setDefaultPageSize(PageSize.A4);
        
       
        float col=280f;
        float columnwidth[]={col,col};
        
        Table table = new Table(columnwidth);
        table.setBackgroundColor(new DeviceRgb(63, 169, 219))
        .setFontColor(Color.WHITE);
        table.addCell(new Cell().add("INVOICE")
        .setTextAlignment(TextAlignment.CENTER)
        .setVerticalAlignment(VerticalAlignment.MIDDLE)
        .setMarginTop(30f)
        .setMarginBottom(30f)
        .setFontSize(30f)
        .setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add("SupplyChain Management\n,kadavanthra junction,\nkochi 682307 ")
        .setTextAlignment(TextAlignment.CENTER)
        .setMarginTop(30f)
        .setMarginBottom(30f)
        .setBorder(Border.NO_BORDER)
        .setMarginRight(10f));
        float columnwidth1[]={80,300,200,80};
        Table table1 = new Table(columnwidth1);
        table1.addCell(new Cell(0,4).add("Customer Information")
        .setBold());
    
        table1.addCell(new Cell().add("Preorder Id ").setBorder(Border.NO_BORDER)) ;
        table1.addCell(new Cell().add(String.valueOf(request.getPreOrder().getPreOrderId())).setBorder(Border.NO_BORDER)) ;
        
    
        table1.addCell(new Cell().add("Purchaser Name ").setBorder(Border.NO_BORDER));
        table1.addCell(new Cell().add(request.getPreOrder().getUser().getName()).setBorder(Border.NO_BORDER));
    
        table1.addCell(new Cell().add("email ").setBorder(Border.NO_BORDER));
        table1.addCell(new Cell().add(request.getPreOrder().getUser().getEmail()).setBorder(Border.NO_BORDER));
       
        table1.addCell(new Cell().add("Address ").setBorder(Border.NO_BORDER)) ;
        table1.addCell(new Cell().add(request.getPreOrder().getAddress()).setBorder(Border.NO_BORDER)) ;
        
         
           
    
    
        float columnwidth2[]={140,180,140,140,140,140};
        Table table2 = new Table(columnwidth2);
        table2.addCell(new Cell().add("ItemName")
        .setBackgroundColor(new DeviceRgb(63, 169, 219))
        .setFontColor(Color.WHITE)
        .setTextAlignment(TextAlignment.RIGHT)
        );
        table2.addCell(new Cell().add("DelieveryBoy Name ")
        .setBackgroundColor(new DeviceRgb(63, 169, 219))
        .setFontColor(Color.WHITE)
        .setTextAlignment(TextAlignment.RIGHT)
        );
        table2.addCell(new Cell().add("Delievery Person no ")
        .setBackgroundColor(new DeviceRgb(63, 169, 219))
        .setFontColor(Color.WHITE)
        .setTextAlignment(TextAlignment.RIGHT)
        );
        table2.addCell(new Cell().add("Quantity")
        .setBackgroundColor(new DeviceRgb(63, 169, 219))
        .setFontColor(Color.WHITE)
        .setTextAlignment(TextAlignment.RIGHT)
        );
    
    
        table2.addCell(new Cell().add("Supplier Name")
        .setBackgroundColor(new DeviceRgb(63, 169, 219))
        .setFontColor(Color.WHITE)
        .setTextAlignment(TextAlignment.RIGHT)
        );
        
    
        table2.addCell(new Cell().add("D date")
        .setBackgroundColor(new DeviceRgb(63, 169, 219))
        .setFontColor(Color.WHITE)
        .setTextAlignment(TextAlignment.RIGHT)
        );
    
    
     
        table2.addCell(new Cell().add(request.getPreOrder().getItem().getItemName()).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().add(request.getPreOrder().getDeliveryPerson().getdPersonName()).setBorder(Border.NO_BORDER));
    
        table2.addCell(new Cell().add(String.valueOf(request.getPreOrder().getDeliveryPerson().getMobile())).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().add(String.valueOf(request.getPreOrder().getQuantity())).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().add(request.getPreOrder().getSupplier().getSupplierName()).setBorder(Border.NO_BORDER)) ;
      table2.addCell(new Cell().add(String.valueOf(request.getPreOrder().getdDate())).setBorder(Border.NO_BORDER)) ;
    
      table2.addCell(new Cell().add("")
      .setBackgroundColor(new DeviceRgb(63, 169, 219))
      .setBorder(Border.NO_BORDER)
      .setFontColor(Color.WHITE)
      );
        
        table2.addCell(new Cell().add("")
        .setBackgroundColor(new DeviceRgb(63, 169, 219))
        .setBorder(Border.NO_BORDER)
        .setFontColor(Color.WHITE)
        );
        table2.addCell(new Cell().add("")
        .setBackgroundColor(new DeviceRgb(63, 169, 219))
        .setBorder(Border.NO_BORDER)
        .setFontColor(Color.WHITE)
        );
        table2.addCell(new Cell().add("")
        .setBackgroundColor(new DeviceRgb(63, 169, 219))
        .setBorder(Border.NO_BORDER)
        .setFontColor(Color.WHITE)
        );
        table2.addCell(new Cell().add("Total Amount")
        .setBackgroundColor(new DeviceRgb(63, 169, 219))
        .setTextAlignment(TextAlignment.RIGHT)
        .setBorder(Border.NO_BORDER)
        .setFontColor(Color.BLACK)
        .setBold()
        );
        table2.addCell(new Cell().add(String.valueOf(request.getPrice()))
        .setBackgroundColor(new DeviceRgb(63, 169, 219))
        .setTextAlignment(TextAlignment.RIGHT)
        .setBorder(Border.NO_BORDER)
        .setFontColor(Color.BLACK)
        .setBold()
        );
    
    
     
        document.add(table);
        document.add(new Paragraph("\n"));
        
        document.add(table1);
        document.add(new Paragraph("\n"));
        document.add(table2);
        document.add(new Paragraph("\n(Verified)").setTextAlignment(TextAlignment.RIGHT));
        document.close();
        System.out.println("pdfcreated");


        PostOrder postorder= postorderRepository.findByPostOrderId(postorderId);
        PreOrder preorder=postorder.getPreorder();
        User user=preorder.getUser();
        String to=user.getEmail();
        this.emailService.sendPdf("Invoice", filename,"This is a mail from Supplychain Mangement management", to);
    System.out.println("??????????????????????????????//DDFF");
     
        
        }
        
        catch (FileNotFoundException e) {
        e.printStackTrace();
        }


       
    }



    @PostMapping("/shipped")
     public void sendEmail4()
     {
        postorderRepository.findAllShipped();
    }



    

    @PostMapping("/delivered")
    public void sendEmailDelievered()
    {
       postorderRepository.findAllDelivered();
   }


    
    @PostMapping("/shipped/{postorderId}")
    public void sendEmail4(@PathVariable ("postorderId") Integer postorderId)
    {
       PostOrder postorder= postorderRepository.findByPostOrderId(postorderId);
       PreOrder preorder=postorder.getPreorder();
       User user=preorder.getUser();
       String to=user.getEmail();
       // PostOrder postorder=postorderRepository.findByPostOrderId(SecurityUtil.getCurrentUserId());
      
       String content="hello";
       String subject="haaai";
       System.out.println(to);
       emailService.sendEmail(subject,content,to);


       // postorderService.orderStatusUpdate1(postorderId);

       postorderService.orderStatusShipped(postorderId);


   }


    @PostMapping("/delivered/{postorderId}")
     public void sendEmail5(@PathVariable ("postorderId") Integer postorderId)
     {
        PostOrder postorder= postorderRepository.findByPostOrderId(postorderId);
        PreOrder preorder=postorder.getPreorder();
        User user=preorder.getUser();
        String to=user.getEmail();
        // PostOrder postorder=postorderRepository.findByPostOrderId(SecurityUtil.getCurrentUserId());
       
        String content="hello";
        String subject="haaai";
        System.out.println(to);
        emailService.sendEmail(subject,content,to);


        // postorderService.orderStatusUpdate1(postorderId);

        postorderService.orderStatusDelivered(postorderId);


    }
}

