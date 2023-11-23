package com.supplychain.supplychain.controller;
import java.util.Collection;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.supplychain.supplychain.entity.Chat;
import com.supplychain.supplychain.form.ChatForm;
import com.supplychain.supplychain.repository.ChatRepository;
import com.supplychain.supplychain.service.ChatService;
import com.supplychain.supplychain.view.ChatListView;


@RestController
@RequestMapping("/chat")
public  class ChatController {
    
    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private ChatService chatService;
    


    @PostMapping
    public ChatListView add(@RequestBody @Valid  ChatForm form){
        System.out.println(form.getSenderId()+" fsdfdfgdfgdf "+form.getReceiverId());
        return chatService.add(form);
    }

    @GetMapping("/chat/{chatId}")
    public Collection<Chat> findChats(@PathVariable("chatId") Integer chatId){
        System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
        return chatService.findChats(chatId);
    } 
}
