package com.abok.tinder_ai_backend;

import com.abok.tinder_ai_backend.chats.ChatMessage;
import com.abok.tinder_ai_backend.conversations.Conversation;
import com.abok.tinder_ai_backend.enumeration.Gender;
import com.abok.tinder_ai_backend.profiles.Profile;
import com.abok.tinder_ai_backend.repository.ConversationRepository;
import com.abok.tinder_ai_backend.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class TinderAiBackendApplication {

	@Autowired
	private ProfileRepository profileRepository;

	@Autowired
	private ConversationRepository conversationRepository;

	public static void main(String[] args) {
		SpringApplication.run(TinderAiBackendApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
			profileRepository.deleteAll();
			conversationRepository.deleteAll();
			Profile profile = new Profile(
					"1",
					"Allan",
					"Abok",
					23,
					"I am a software engineer specialized in JavaScript frameworks like ReactJs",
					"Luo",
					Gender.MALE,
					"allan.jpg",
					"INTP"
			);
		Profile	profile1 = new Profile(
					"2",
					"Kevin",
					"Abok",
					20,
					"I am a software engineer specialized in JavaScript frameworks like ReactJs",
					"Luo",
					Gender.MALE,
					"kevin.jpg",
					"Yes"
			);
			profileRepository.save(profile);
			profileRepository.save(profile1);
			System.out.println("Profiles:");
			profileRepository.findAll().forEach(System.out::println);

			Conversation conversation = new Conversation(
					"1",
					profile.id(),
					List.of(
							new ChatMessage(
									"Hey there",
									profile.id(),
									LocalDateTime.now()
							)
					)
			);
			conversationRepository.save(conversation);
			System.out.println("Conversations:");
			conversationRepository.findAll().forEach(System.out::println);
		};
	}
}