package com.tech.exam.controller;

import com.tech.exam.dto.request.SetPinRequest;
import com.tech.exam.dto.response.CardResponse;
import com.tech.exam.service.CardService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/v1/card")
@FieldDefaults(level = PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CardController {

    CardService cardService;

    @PostMapping("/{customerId}")
    @ResponseStatus(CREATED)
    public CardResponse createCard(@PathVariable Long customerId) {
        return cardService.createCard(customerId);
    }

    @GetMapping("/{cardNumber}")
    @ResponseStatus(OK)
    public CardResponse getCardByCardNumber(@PathVariable String cardNumber) {
        return cardService.getCardByCardNumber(cardNumber);
    }

    @PutMapping("/{customerId}")
    @ResponseStatus(OK)
    public void setPin(@PathVariable Long customerId, @RequestBody SetPinRequest request) {
        cardService.setPin(customerId, request);
    }

    @DeleteMapping("/{cardNumber}")
    @ResponseStatus(NO_CONTENT)
    public void deleteCardById(@PathVariable String cardNumber) {
        cardService.deleteCardById(cardNumber);
    }


}
