package com.tech.exam.converter;

import com.tech.exam.dto.response.CustomerResponse;
import com.tech.exam.model.Customer;

public class CustomerResponseConverter {
    public static CustomerResponse convertToCustomerResponse(Customer customer) {
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
