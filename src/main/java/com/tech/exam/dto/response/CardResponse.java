package com.tech.exam.dto.response;

import java.time.LocalDate;

public record CardResponse(
        Long id,
        Long costumerId,
        String cardNumber,
        LocalDate createDate,
        LocalDate expiryDate
) {
}
