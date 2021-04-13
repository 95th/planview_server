package com.planview.server.service;

import java.util.List;

import javax.transaction.Transactional;

import com.planview.server.entity.Message;
import com.planview.server.entity.MessageView;
import com.planview.server.repos.MessageRepo;

import org.springframework.stereotype.Service;

@Transactional
@Service
public class MessageService {
    private final MessageRepo messageRepo;

    public MessageService(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    public Message createMessage(Message message) {
        return this.messageRepo.save(message);
    }

    public List<MessageView> getMessages() {
        var userId = AuthService.getCurrentUserId();
        return this.messageRepo.findAllByRecipient(userId);
    }

    public void deleteMessage(int messageId) {
        this.messageRepo.deleteById(messageId);
    }
}
