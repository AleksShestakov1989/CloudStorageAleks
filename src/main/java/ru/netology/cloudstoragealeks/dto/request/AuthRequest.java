package ru.netology.cloudstoragealeks.dto.request;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


public record AuthRequest(
        @NotBlank(message = "login must not be null")
        String login,

        @NotBlank(message = "password must not be null")
        @Size(min = 2, max = 30, message = "Password should be between 2 and 30 characters")
        String password
) {
}
