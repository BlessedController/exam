package com.tech.exam.controller;

import com.tech.exam.dto.request.CreateCustomerRequest;
import com.tech.exam.dto.request.UpdateCustomerRequest;
import com.tech.exam.dto.response.CustomerResponse;
import com.tech.exam.service.CustomerService;
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
@RequestMapping("/v1/customer")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CustomerController {
    CustomerService customerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResponse createCustomer(CreateCustomerRequest request) {
        return customerService.createCustomer(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CustomerResponse getCustomerById(Long id) {
        return customerService.getCustomerById(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public CustomerResponse updateCustomerById(Long id, UpdateCustomerRequest request) {
        return customerService.updateCustomerById(id, request);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomerById(Long id) {
        customerService.deleteCustomerById(id);
    }


}
