package com.planview.server.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "sender", nullable = false)
    @NotNull
    private String sender;

    @Column(name = "recipient", nullable = false)
    @NotNull
    private String recipient;

    @Column(name = "subject", nullable = false)
    @NotNull
    private String subject;

    @Column(name = "body", nullable = false)
    @NotNull
    private String body;

    @Column(name = "date", nullable = false, columnDefinition = "DATE")
    @NotNull
    private LocalDate date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Message [body=" + body + ", date=" + date + ", id=" + id + ", recipient=" + recipient + ", sender="
                + sender + ", subject=" + subject + "]";
    }
}
