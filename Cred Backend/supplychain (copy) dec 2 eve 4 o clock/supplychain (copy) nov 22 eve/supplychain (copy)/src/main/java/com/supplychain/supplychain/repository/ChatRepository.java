package com.supplychain.supplychain.repository;
import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.supplychain.supplychain.entity.Chat;
public interface ChatRepository extends Repository< Chat, Integer>{

    Chat save(Chat chat) ;
    Collection<Chat>findById(Integer chatId);
    
    @Query(value="SELECT * FROM chat WHERE sender_id=?1 AND receiver_id=?2 OR sender_id=?2 AND receiver_id=?1 ORDER BY create_date",nativeQuery = true)
    Collection<Chat>findallChats(Integer senderId,Integer receiverId);
}
