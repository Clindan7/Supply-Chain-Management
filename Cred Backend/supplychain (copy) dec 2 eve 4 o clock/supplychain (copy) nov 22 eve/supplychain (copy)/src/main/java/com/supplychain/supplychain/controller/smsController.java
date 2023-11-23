package com.supplychain.supplychain.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


@RestController
public class smsController {
    @GetMapping(value = "/sendSMS")
        public ResponseEntity<String> sendSMS() {
System.out.println("$$$$$$$$$$$4");
                Twilio.init("ACf329f9e86a04b89baae0fe26e5fadb77","eb3151587787835762d071c9ce2447c4");
System.out.println("xxxxxxxxxxx");
                Message.creator(new PhoneNumber("+918921553315"),
                                new PhoneNumber("+19254815848"), "Hello from Twilio").create();
System.out.println("YYYYYYYYYYYYYYy");
                return new ResponseEntity<String>("Message sent successfully", HttpStatus.OK);
        }
}
