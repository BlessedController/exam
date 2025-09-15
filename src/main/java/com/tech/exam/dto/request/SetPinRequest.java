package com.tech.exam.dto.request;

import jakarta.validation.constraints.NotBlank;

public record SetPinRequest (
        @NotBlank(message = "pin cannot be blank")
        String pin
){
}
