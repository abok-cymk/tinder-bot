package com.abok.tinder_ai_backend.conversations;

import java.util.List;
import com.abok.tinder_ai_backend.chats.ChatMessage;

public record Conversation(
        String id,
        String profileId,
        List<ChatMessage> messages
) {
}
