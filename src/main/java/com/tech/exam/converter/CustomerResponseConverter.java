package com.tech.exam.converter;

import com.tech.exam.dto.response.CustomerResponse;
import com.tech.exam.model.CustomerEntity;

public class CustomerResponseConverter {
    public static CustomerResponse convertToCustomerResponse(CustomerEntity customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getFinCode(),
                customer.getName(),
                customer.getSurname(),
                customer.getCardNumber(),
                customer.getStatus()
        );
    }
}
