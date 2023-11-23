package com.supplychain.supplychain.service;
import java.util.Collection;

import com.supplychain.supplychain.entity.Chat;
import com.supplychain.supplychain.form.ChatForm;
import com.supplychain.supplychain.view.ChatListView;

public interface ChatService {
        
    ChatListView add(ChatForm form);
    Collection<Chat> findChats(Integer chatterId);
}
