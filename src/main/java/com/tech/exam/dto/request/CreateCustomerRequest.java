package com.tech.exam.dto.request;

public record CreateCustomerRequest(
        String name,
        String surname,
        String finCode
) {
}
