package com.abok.tinder_ai_backend.profiles;

import com.abok.tinder_ai_backend.enumeration.Gender;

public record Profile(
        String id,
        String firstName,
        String lastName,
        int age,
        String bio,
        String ethnicity,
        Gender gender,
        String imageUrl,
        String myersBriggsPersonalityType
) {
}
