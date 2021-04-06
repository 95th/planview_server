package com.planview.server.entity;

import java.time.LocalDateTime;

public class MessageView {
    private final int id;
    private final int sender;
    private final String senderName;
    private final String subject;
    private final String body;
    private final LocalDateTime date;

    public MessageView(Message message, User user) {
        this.id = message.getId();
        this.sender = message.getSender();
        this.senderName = user.getUserName();
        this.subject = message.getSubject();
        this.body = message.getBody();
        this.date = message.getDate();
    }

    public int getId() {
        return id;
    }

    public int getSender() {
        return sender;
    }

    public String getSenderName() {
        return senderName;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }

    public LocalDateTime getDate() {
        return date;
    }

}
