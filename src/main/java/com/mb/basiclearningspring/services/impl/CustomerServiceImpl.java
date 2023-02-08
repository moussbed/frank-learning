package com.mb.basiclearningspring.services.impl;

import com.mb.basiclearningspring.exceptions.NotFoundException;
import com.mb.basiclearningspring.repositories.CustomerRepository;
import com.mb.basiclearningspring.dto.mappers.CustomerMapper;
import com.mb.basiclearningspring.dto.CustomerRequest;
import com.mb.basiclearningspring.dto.CustomerResponse;
import com.mb.basiclearningspring.entities.Customer;
import com.mb.basiclearningspring.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerResponse> getCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(CustomerMapper.MAPPER::customerToCustomerResponse)
                .toList();
    }

    @Override
    public void addCustomer(CustomerRequest request) {
        Customer customer = CustomerMapper.MAPPER.customerRequestToCustomer(request);
        customerRepository.save(customer);
    }

    @Override
    public CustomerResponse getCustomer(Long id) {
        Customer customer = getCustomerById(id);
        return CustomerMapper.MAPPER.customerToCustomerResponse(customer);
    }

    @Override
    public CustomerResponse modifyCustomer(Long id, CustomerRequest customerRequest){
        Customer customer = getCustomerById(id);
        customer.setFirstName(customerRequest.firstName());
        customer.setLastName(customerRequest.lastName());
        customer = customerRepository.save(customer);

        return CustomerMapper.MAPPER.customerToCustomerResponse(customer);
    }

    private Customer getCustomerById(Long id)  {
        return customerRepository.findById(id).orElseThrow(()->new NotFoundException(String.format("Customer %s not found ",id)));
    }

}
