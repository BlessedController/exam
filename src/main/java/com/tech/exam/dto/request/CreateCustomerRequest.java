package com.tech.exam.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CreateCustomerRequest(
        @NotBlank(message = "name cannot be blank")
        String name,

        @NotBlank(message = "surname cannot be blank")
        String surname,

        @NotBlank(message = "fincode cannot be blank")
        String finCode
) {
}
