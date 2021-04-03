package com.planview.server.repos;

import java.util.List;

import com.planview.server.entity.Message;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepo extends JpaRepository<Message, Integer> {
    List<Message> findAllByRecipient(String recipient);
}
