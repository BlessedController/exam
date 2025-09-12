package com.tech.exam.converter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tech.exam.dto.response.CardResponse;
import com.tech.exam.model.Card;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
public class CardResponseConverter {

    public static CardResponse convertToCardResponse(Card card) {

        return new CardResponse(
                card.getId(),
                card.getCostumerId(),
                card.getCardNumber(),
                card.getCreateDate(),
                card.getExpiryDate()
        );
    }

}
