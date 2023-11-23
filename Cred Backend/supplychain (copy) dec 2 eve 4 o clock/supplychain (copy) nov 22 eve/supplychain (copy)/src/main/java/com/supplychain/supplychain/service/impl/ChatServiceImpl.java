package com.supplychain.supplychain.service.impl;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.supplychain.supplychain.entity.Chat;
import com.supplychain.supplychain.form.ChatForm;
import com.supplychain.supplychain.repository.ChatRepository;
import com.supplychain.supplychain.security.util.SecurityUtil;

import com.supplychain.supplychain.service.ChatService;
import com.supplychain.supplychain.view.ChatListView;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService{

    @Autowired
    private ChatRepository chatRepository;

    @Override
    public ChatListView add(ChatForm form) {

        return new ChatListView(chatRepository.save(new Chat(form)));
    }

    @Override
    public Collection<Chat> findChats(Integer chatId){
        
        System.out.println(SecurityUtil.getCurrentUserId()+" xxxxxxxxxxxxxx "+chatId);
        return chatRepository.findallChats(SecurityUtil.getCurrentUserId(),chatId);
    }
}
