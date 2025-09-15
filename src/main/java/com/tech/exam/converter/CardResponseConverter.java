package com.tech.exam.converter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tech.exam.dto.response.CardResponse;
import com.tech.exam.model.CardEntity;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
public class CardResponseConverter {

    public static CardResponse convertToCardResponse(CardEntity cardEntity) {

        return new CardResponse(
                cardEntity.getId(),
                cardEntity.getCostumerId(),
                cardEntity.getCardNumber(),
                cardEntity.getCreateDate(),
                cardEntity.getExpiryDate()
        );
    }

}
