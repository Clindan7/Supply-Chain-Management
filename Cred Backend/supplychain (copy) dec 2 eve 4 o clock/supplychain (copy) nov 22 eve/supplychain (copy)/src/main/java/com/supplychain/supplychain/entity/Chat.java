package com.supplychain.supplychain.entity;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TemporalType;

import com.supplychain.supplychain.form.ChatForm;

import javax.persistence.Temporal;
import javax.persistence.GeneratedValue;
@Entity
public class Chat {
    public static enum Status {
        INACTIVE((byte) 0),
        ACTIVE((byte) 1);

        public final byte value;

        private Status(byte value) {
            this.value = value;
        }
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer chatId;

    private Integer senderId;

    private String message;

    private Integer receiverId;

    
    private byte status;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;


    public Chat(){

    }

    
    public Chat(Integer chatId, Integer senderId, String message, Integer receiverId, byte status, Date createDate) {
        this.chatId = chatId;
        this.senderId = senderId;
        this.message = message;
        this.receiverId = receiverId;
        this.status = status;
        this.createDate = createDate;
    }


    


    public Chat(ChatForm form) {
        this.senderId = form.getSenderId();
        this.message = form.getMessage();
        this.receiverId = form.getReceiverId();

        this.status = Status.ACTIVE.value;

        Date dt = new Date();
        this.createDate = dt;
    }


    public Integer getChatId() {
        return chatId;
    }

    public void setChatId(Integer chatId) {
        this.chatId = chatId;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

}
