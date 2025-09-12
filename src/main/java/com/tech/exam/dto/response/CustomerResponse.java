package com.tech.exam.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tech.exam.model.enums.CustomerStatus;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
public record CustomerResponse(
        Long id,

        String finCode,

        String name,

        String surname,

        String cardNumber,

        CustomerStatus status
) {
}
