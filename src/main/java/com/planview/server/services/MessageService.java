package com.planview.server.services;

import java.util.List;

import javax.validation.Valid;

import com.planview.server.entity.Message;
import com.planview.server.repos.MessageRepo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/message")
public class MessageService {
    private final MessageRepo messageRepo;

    public MessageService(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    @PostMapping
    public Message createMessage(@RequestBody @Valid Message message) {
        return this.messageRepo.save(message);
    }

    @GetMapping
    public List<Message> getMessages(@RequestParam String recipient) {
        return this.messageRepo.findAllByRecipient(recipient);
    }

}
