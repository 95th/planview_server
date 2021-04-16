package com.planview.server.controller;

import java.util.List;

import javax.validation.Valid;

import com.planview.server.entity.Message;
import com.planview.server.entity.MessageView;
import com.planview.server.service.MessageService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/message")
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    public Message createMessage(@RequestBody @Valid Message message) {
        return this.messageService.createMessage(message);
    }

    @GetMapping("inbox")
    public List<MessageView> getInbox() {
        return this.messageService.getInbox();
    }

    @DeleteMapping("{messageId}")
    public void deleteMessage(@PathVariable int messageId) {
        this.messageService.deleteMessage(messageId);
    }

}
