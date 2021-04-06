package com.planview.server.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "sender", nullable = false)
    @NotNull
    private int sender;

    @Column(name = "recipient", nullable = false)
    @NotNull
    private int recipient;

    @Column(name = "subject", nullable = false)
    @NotNull
    private String subject;

    @Column(name = "body", nullable = false)
    @NotNull
    private String body;

    @Column(name = "date", nullable = false, columnDefinition = "DATE")
    @NotNull
    private LocalDateTime date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSender() {
        return sender;
    }

    public void setSender(int sender) {
        this.sender = sender;
    }

    public int getRecipient() {
        return recipient;
    }

    public void setRecipient(int recipient) {
        this.recipient = recipient;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Message [body=" + body + ", date=" + date + ", id=" + id + ", recipient=" + recipient + ", sender="
                + sender + ", subject=" + subject + "]";
    }
}
