package com.luizmatias.findadev.domain.entities;

import java.util.Date;

public class Message {
    private Long id;
    private Chat chat;
    private User from;
    private User to;
    private String message;
    private Date sentAt;

    public Message() {
    }

    public Message(Long id, Chat chat, User from, User to, String message, Date sentAt) {
        this.id = id;
        this.chat = chat;
        this.from = from;
        this.to = to;
        this.message = message;
        this.sentAt = sentAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public User getTo() {
        return to;
    }

    public void setTo(User to) {
        this.to = to;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getSentAt() {
        return sentAt;
    }

    public void setSentAt(Date sentAt) {
        this.sentAt = sentAt;
    }
}
