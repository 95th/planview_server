package com.planview.server.controller;

import java.util.List;

import javax.validation.Valid;

import com.planview.server.entity.Message;
import com.planview.server.entity.MessageView;
import com.planview.server.repos.MessageRepo;
import com.planview.server.service.AuthService;

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
    private final MessageRepo messageRepo;

    public MessageController(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    @PostMapping
    public Message createMessage(@RequestBody @Valid Message message) {
        return this.messageRepo.save(message);
    }

    @GetMapping
    public List<MessageView> getMessages() {
        var userId = AuthService.getCurrentUserId();
        return this.messageRepo.findAllByRecipient(userId);
    }

    @DeleteMapping("{messageId}")
    public void deleteMessage(@PathVariable int messageId) {
        this.messageRepo.deleteById(messageId);
    }

}
