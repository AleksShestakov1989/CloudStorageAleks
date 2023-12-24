package ru.netology.cloudstoragealeks.dto.request;


import javax.validation.constraints.NotBlank;

public record FileNameEditRequest(@NotBlank(message = "login must not be null") String filename) {
}
