package com.tech.exam.service;

import com.tech.exam.dto.request.SetPinRequest;
import com.tech.exam.dto.response.CardResponse;
import com.tech.exam.exception.NotFoundException;
import com.tech.exam.model.CardEntity;
import com.tech.exam.model.CustomerEntity;
import com.tech.exam.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Random;

import static com.tech.exam.converter.CardResponseConverter.convertToCardResponse;
import static com.tech.exam.model.enums.CardStatus.DELETED;
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

        CardEntity cardEntity = CardEntity.builder()
                .costumerId(customerId)
                .cardNumber(cardNumber)
                .cvv(random.nextInt(100, 1000))
                .pin(random.nextInt(1000, 10000) + "")
                .build();

        cardRepository.save(cardEntity);

        CustomerEntity customerById = customerService.findCustomerById(customerId);

        customerById.setCardNumber(cardNumber);

        customerService.addCardToCustomer(customerById);

        return convertToCardResponse(cardEntity);
    }

    public CardResponse getCardByCardNumber(String cardNumber) {

        CardEntity cardEntity = findCardByCardNumber(cardNumber);

        return convertToCardResponse(cardEntity);
    }

    public void setPin(Long customerId, SetPinRequest request) {

        CustomerEntity customer = customerService.findCustomerById(customerId);

        String cardNumber = customer.getCardNumber();

        CardEntity cardEntity = findCardByCardNumber(cardNumber);

        cardEntity.setPin(request.pin());

        cardRepository.save(cardEntity);
    }

    public void deleteCardById(String cardNumber) {
        CardEntity cardEntity = findCardByCardNumber(cardNumber);
        cardEntity.setStatus(DELETED);
    }

    protected CardEntity findCardByCardNumber(String cardNumber) {
        return cardRepository.findCardByCardNumber(cardNumber).orElseThrow(() ->
                new NotFoundException("Card not found by card number: " + cardNumber));
    }
}
