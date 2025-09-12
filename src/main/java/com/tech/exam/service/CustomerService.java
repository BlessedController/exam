package com.tech.exam.service;

import com.tech.exam.dto.request.CreateCustomerRequest;
import com.tech.exam.dto.request.UpdateCustomerRequest;
import com.tech.exam.dto.response.CustomerResponse;
import com.tech.exam.exception.CustomerNotFoundException;
import com.tech.exam.model.Customer;
import com.tech.exam.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import static com.tech.exam.converter.CustomerResponseConverter.convertToCustomerResponse;
import static com.tech.exam.model.enums.CustomerStatus.ACTIVE;
import static com.tech.exam.model.enums.CustomerStatus.DELETED;
import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class CustomerService {

    CustomerRepository customerRepository;

    public CustomerResponse createCustomer(CreateCustomerRequest request) {

        Customer customer = Customer.builder()
                .name(request.name())
                .surname(request.surname())
                .finCode(request.finCode())
                .status(ACTIVE)
                .build();

        customerRepository.save(customer);

        return convertToCustomerResponse(customer);
    }

    public CustomerResponse getCustomerById(Long id) {
        Customer customer = findCustomerById(id);
        return convertToCustomerResponse(customer);
    }

    public CustomerResponse updateCustomerById(Long id, UpdateCustomerRequest request) {

        Customer customer = findCustomerById(id);

        if (StringUtils.isNotEmpty(request.name())) {
            customer.setName(request.name());
        }
        if (StringUtils.isNotEmpty(request.surname())) {
            customer.setSurname(request.surname());
        }

        customerRepository.save(customer);

        return convertToCustomerResponse(customer);
    }

    public void deleteCustomerById(Long id) {
        Customer customer = findCustomerById(id);
        customer.setStatus(DELETED);
        customerRepository.save(customer);
    }

    protected Customer findCustomerById(Long id) {

        Customer customer = customerRepository.findById(id).orElseThrow(()
                -> new CustomerNotFoundException("Customer not found by id" + id));

        if (customer.getStatus() == DELETED) {
            throw new CustomerNotFoundException("Customer not found by id" + id);
        }

        return customer;
    }


}
