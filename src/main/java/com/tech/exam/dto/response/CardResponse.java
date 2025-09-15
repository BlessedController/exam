package com.tech.exam.dto.response;

import java.time.LocalDateTime;

public record CardResponse(
        Long id,
        Long costumerId,
        String cardNumber,
        LocalDateTime createDate,
        LocalDateTime expiryDate
) {
}
