package com.supplychain.supplychain.view;
import java.util.Date;
import com.supplychain.supplychain.json.Json;
import com.supplychain.supplychain.entity.Chat;
public class ChatListView {
    private  Integer chatId;
    private  Integer senderId;
    private  Integer receiverId;
    private  String message;

    @Json.DateTimeFormat
    private Date createDate;

    public ChatListView(Integer chatId, Integer senderId, Integer receiverId, String message, Date createDate) {
        this.chatId = chatId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.message = message;
        this.createDate = createDate;
    }

    public ChatListView(Chat chat) {

        this.chatId=chat.getChatId();
        this.senderId=chat.getSenderId();
        this.receiverId=chat.getReceiverId();
        this.message=chat.getMessage();
        this.createDate=chat.getCreateDate();
    }

    public Integer getChatId() {
        return chatId;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public String getMessage() {
        return message;
    }

    public Date getCreateDate() {
        return createDate;
    }
}
