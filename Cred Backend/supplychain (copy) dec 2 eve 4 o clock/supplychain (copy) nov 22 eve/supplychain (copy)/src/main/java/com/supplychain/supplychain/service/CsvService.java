package com.supplychain.supplychain.service;
import java.io.IOException;
import java.io.Writer;
import java.util.Collection;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.supplychain.supplychain.Helper.CsvHelper;
import com.supplychain.supplychain.entity.Item;
import com.supplychain.supplychain.entity.PostOrder;
import com.supplychain.supplychain.entity.PreOrder;
import com.supplychain.supplychain.repository.CsvRepository;
import com.supplychain.supplychain.repository.PostOrderRepository;
import com.supplychain.supplychain.repository.PreOrderRepository;

import ch.qos.logback.classic.Logger;

@Service
public class CsvService {
    @Autowired
  CsvRepository repository;

  @Autowired
  PostOrderRepository postorderrepo;



  public void save(MultipartFile file) {
    try {
      List<Item> item = CsvHelper.csvToItems(file.getInputStream());
      repository.saveAll(item);
    } catch (IOException e) {
      throw new RuntimeException("fail to store csv data: " + e.getMessage());
    }
  }




  private static final Logger log = getLogger(CsvService.class);
   private static Logger getLogger(Class<CsvService> class1) {
      return null;
    }

    public void writeItemsToCsv(Writer writer) {

        List<Item> items = repository.findAll();
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
          csvPrinter.printRecord("ItemName","Description","Type");
            for (Item item : items) {
                csvPrinter.printRecord(item.getItemName(), item.getDescription(),
                item.getType());
            }
        } catch (IOException e) {
            log.error("Error While writing CSV ", e);
        }
    }



        public void writePostorderToCsv(Writer writer) {
          Collection<PostOrder> postOrders = postorderrepo.findAll();
          try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {

            csvPrinter.printRecord("ItemName","PostorderId","PreorderId","Quantity","price","Address");
              for (PostOrder postOrder : postOrders) {
                  csvPrinter.printRecord(
                    postOrder.getPreOrder().getItem().getItemName(),
                    postOrder.getPostOrderId(),
                    postOrder.getPreOrder().getPreOrderId(),
                  postOrder.getPreOrder().getQuantity(), 
                  postOrder.getPrice(), 
                  postOrder.getPreorder().getAddress());
              }
          } catch (IOException e) {
             
          }
      }






      

      public void writePreorderToCsv(Writer writer) {
        Collection<PostOrder> postOrders = postorderrepo.findAll();
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {

          csvPrinter.printRecord("PostorderId","itemName","Price","Address","Quantity","Ddate");
            for (PostOrder postOrder : postOrders) {
                csvPrinter.printRecord(

                  postOrder.getPostOrderId(),
                  postOrder.getPreOrder().getItem().getItemName(),
                  postOrder.getPrice(),
                    
                postOrder.getPreorder().getAddress(),
                postOrder.getPreOrder().getQuantity(),
                 postOrder.getPreorder().getdDate());
            
           
            }
        } catch (IOException e) {
           
        }
    }

}



  


