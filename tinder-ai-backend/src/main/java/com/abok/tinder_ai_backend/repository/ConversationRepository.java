package com.abok.tinder_ai_backend.repository;

import com.abok.tinder_ai_backend.conversations.Conversation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConversationRepository extends MongoRepository<Conversation, String> {
}
