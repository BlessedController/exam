package com.tech.exam.controller;

import com.tech.exam.dto.request.SetPinRequest;
import com.tech.exam.dto.response.CardResponse;
import com.tech.exam.service.CardService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/card")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CardController {

    CardService cardService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CardResponse createCard(Long customerId) {
        return cardService.createCard(customerId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CardResponse getCardByCardNumber(String cardNumber) {
        return cardService.getCardByCardNumber(cardNumber);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public String setPin(Long customerId, SetPinRequest request) {
        return cardService.setPin(customerId, request);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCardById(String cardNumber) {
        cardService.deleteCardById(cardNumber);
    }


}
