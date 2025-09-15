package com.tech.exam.service;

import com.tech.exam.dto.request.CreateCustomerRequest;
import com.tech.exam.dto.request.UpdateCustomerRequest;
import com.tech.exam.dto.response.CustomerResponse;
import com.tech.exam.exception.NotFoundException;
import com.tech.exam.model.CardEntity;
import com.tech.exam.model.CustomerEntity;
import com.tech.exam.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import static com.tech.exam.converter.CustomerResponseConverter.convertToCustomerResponse;
import static com.tech.exam.model.enums.CustomerStatus.ACTIVE;
import static com.tech.exam.model.enums.CustomerStatus.DELETED;
import static com.tech.exam.model.enums.CustomerStatus.IN_PROGRESS;
import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class CustomerService {

    CustomerRepository customerRepository;

    public CustomerResponse createCustomer(CreateCustomerRequest request) {

        CustomerEntity customer = CustomerEntity.builder()
                .name(request.name())
                .surname(request.surname())
                .finCode(request.finCode())
                .status(ACTIVE)
                .build();

        customerRepository.save(customer);

        return convertToCustomerResponse(customer);
    }

    public CustomerResponse getCustomerById(Long id) {
        CustomerEntity customer = findCustomerById(id);
        return convertToCustomerResponse(customer);
    }

    public CustomerResponse updateCustomerById(Long id, UpdateCustomerRequest request) {

        CustomerEntity customer = findCustomerById(id);

        if (StringUtils.isNotEmpty(request.name())) {
            customer.setName(request.name());
        }
        if (StringUtils.isNotEmpty(request.surname())) {
            customer.setSurname(request.surname());
        }

        customer.setStatus(IN_PROGRESS);

        customerRepository.save(customer);

        return convertToCustomerResponse(customer);
    }

    public void deleteCustomerById(Long id) {
        CustomerEntity customer = findCustomerById(id);
        customer.setStatus(DELETED);
        customerRepository.save(customer);
    }

    protected CustomerEntity findCustomerById(Long id) {

        CustomerEntity customer = customerRepository.findById(id).orElseThrow(()
                -> new NotFoundException("Customer not found by id" + id));

        if (customer.getStatus() == DELETED) {
            throw new NotFoundException("Customer not found by id" + id);
        }

        return customer;
    }

    protected void addCardToCustomer(CustomerEntity customer) {
        customerRepository.save(customer);
    }


}
