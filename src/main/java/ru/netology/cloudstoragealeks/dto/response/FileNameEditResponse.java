package ru.netology.cloudstoragealeks.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record FileNameEditResponse(@JsonProperty("auth-token") String name) {
}
