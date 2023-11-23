package com.supplychain.supplychain.controller;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.supplychain.supplychain.Helper.CsvHelper;

import com.supplychain.supplychain.entity.ResponseMessage;
import com.supplychain.supplychain.service.CsvService;


@Controller
@RequestMapping("/api/csv")
public class CsvController {

    @Autowired
  CsvService fileService;


@GetMapping("/download")
  public void getAllItemsInCsv(HttpServletResponse servletResponse) throws IOException {
      servletResponse.setContentType("text/csv");
      servletResponse.addHeader("Content-Disposition","attachment; filename=\"itemlist.csv\"");
      fileService.writeItemsToCsv(servletResponse.getWriter());
  }

  @GetMapping("/preordercsv")
  public void getAllPreorderInCsv(HttpServletResponse servletResponse) throws IOException {
      servletResponse.setContentType("text/csv");
      servletResponse.addHeader("Content-Disposition","attachment; filename=\"preorderlist.csv\"");
      fileService.writePreorderToCsv(servletResponse.getWriter());
  }

  @GetMapping("/postordercsv")
  public void getAllPostorderInCsv(HttpServletResponse servletResponse) throws IOException {
      servletResponse.setContentType("text/csv");
      servletResponse.addHeader("Content-Disposition","attachment; filename=\"postorderlist.csv\"");
      fileService.writePostorderToCsv(servletResponse.getWriter());
  }




  @PostMapping("/upload")
  public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
    String message = "";

    if (CsvHelper.hasCSVFormat(file)) {
      try {
        fileService.save(file);

        message = "Uploaded the file successfully: " + file.getOriginalFilename();
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
      } catch (Exception e) {
        message = "Could not upload the file: " + file.getOriginalFilename() + "!";
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
      }
    }

    message = "Please upload a csv file!";
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
  }


}


    
