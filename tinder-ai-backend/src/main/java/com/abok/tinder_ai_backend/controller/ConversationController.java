package com.abok.tinder_ai_backend.controller;

import com.abok.tinder_ai_backend.chats.ChatMessage;
import com.abok.tinder_ai_backend.conversations.Conversation;
import com.abok.tinder_ai_backend.repository.ConversationRepository;
import com.abok.tinder_ai_backend.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ConversationController {
    private final ConversationRepository conversationRepository;
    private final ProfileRepository profileRepository;

    @PostMapping("/conversations")
    public Conversation createNewConversation(@RequestBody CreateConversationRequest request) {
        profileRepository.findById(request.profileId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Unable to find a profile with ID " + request.profileId()));
        Conversation conversation = new Conversation(
                UUID.randomUUID().toString(),
                request.profileId(),
                new ArrayList<>()
        );
        conversationRepository.save(conversation);
        return conversation;
    }

    @PostMapping("/conversations/{conversationId}")
    public Conversation addMessageToConversation(
            @PathVariable String conversationId,
            @RequestBody ChatMessage chatMessage) {
     Conversation conversation =  conversationRepository.findById(conversationId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Unable to find conversation with the ID " + conversationId));

     profileRepository.findById(chatMessage.authorId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Unable to find a profile with ID " + chatMessage.authorId()));
     // TODO: Need to validate that the author of a message happens to be only the profile associated with the user
     ChatMessage messageWithTime = new ChatMessage(
            chatMessage.messageText(),
            chatMessage.authorId(),
            LocalDateTime.now()
     );
     conversation.messages().add(messageWithTime);
     conversationRepository.save(conversation);
     return conversation;
    }

    @GetMapping("/conversations/{conversationId}")
    public Conversation getConversation(@PathVariable String conversationId) {
        return conversationRepository.findById(conversationId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find conversation with ID " + conversationId));
    }

    public record CreateConversationRequest(
            String profileId
    ) {}
}
