package ru.netology.cloudstoragealeks.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;


public record AuthResponse(@JsonProperty("auth-token") String authToken) {
}
