package com.tech.exam.service;

import com.tech.exam.dto.request.SetPinRequest;
import com.tech.exam.dto.response.CardResponse;
import com.tech.exam.exception.CardNotFoundException;
import com.tech.exam.model.Card;
import com.tech.exam.model.Customer;
import com.tech.exam.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Random;

import static com.tech.exam.converter.CardResponseConverter.convertToCardResponse;
import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class CardService {

    CardRepository cardRepository;
    CustomerService customerService;

    public CardResponse createCard(Long customerId) {

        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 16; i++) {
            sb.append(random.nextInt(10));
        }

        String cardNumber = sb.toString();

        Card card = Card.builder()
                .costumerId(customerId)
                .cardNumber(cardNumber)
                .cvv(random.nextInt(100, 1000))
                .pin(random.nextInt(1000, 10000))
                .build();

        cardRepository.save(card);

        return convertToCardResponse(card);
    }

    public CardResponse getCardByCardNumber(String cardNumber) {

        Card card = findCardByCardNumber(cardNumber);

        return convertToCardResponse(card);
    }

    public String setPin(Long customerId, SetPinRequest request) {

        Customer customer = customerService.findCustomerById(customerId);

        String cardNumber = customer.getCardNumber();

        Card card = findCardByCardNumber(cardNumber);


        card.setPin(request.pin());

        cardRepository.save(card);

        return "Pin has set successfully";
    }

    public void deleteCardById(String cardNumber) {

        Card card = findCardByCardNumber(cardNumber);

        cardRepository.delete(card);
    }

    protected Card findCardByCardNumber(String cardNumber) {
        return cardRepository.findCardByCardNumber(cardNumber).orElseThrow(() ->
                new CardNotFoundException("Card not found by card number: " + cardNumber));
    }
}
