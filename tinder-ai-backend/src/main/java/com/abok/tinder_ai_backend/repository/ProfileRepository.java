package com.abok.tinder_ai_backend.repository;

import com.abok.tinder_ai_backend.profiles.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProfileRepository extends MongoRepository<Profile, String> {
}
