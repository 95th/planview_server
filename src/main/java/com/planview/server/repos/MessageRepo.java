package com.planview.server.repos;

import java.util.List;

import com.planview.server.entity.Message;
import com.planview.server.entity.MessageView;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MessageRepo extends JpaRepository<Message, Integer> {
    @Query("select new com.planview.server.entity.MessageView(m, u)"
        + " from Message m join User u on m.sender = u.id"
        + " where m.recipient = :recipient"
        + " order by m.id")
    List<MessageView> findAllByRecipient(int recipient);
}
