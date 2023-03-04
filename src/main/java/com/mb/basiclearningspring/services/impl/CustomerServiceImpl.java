package com.mb.basiclearningspring.services.impl;

import com.mb.basiclearningspring.dto.AttachAccountRequest;
import com.mb.basiclearningspring.dto.CustomerRequest;
import com.mb.basiclearningspring.dto.CustomerResponse;
import com.mb.basiclearningspring.dto.mappers.CustomerMapper;
import com.mb.basiclearningspring.entities.Account;
import com.mb.basiclearningspring.entities.Customer;
import com.mb.basiclearningspring.exceptions.NotFoundException;
import com.mb.basiclearningspring.repositories.AccountRepository;
import com.mb.basiclearningspring.repositories.CustomerRepository;
import com.mb.basiclearningspring.services.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final AccountRepository accountRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository, AccountRepository accountRepository) {
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public List<CustomerResponse> getCustomers() {
        // TODO CALL EXTERNAL API TO RETRIEVE CUSTOMERS
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
    public CustomerResponse getCustomer(UUID id) {
        Customer customer = getCustomerById(id);
        return CustomerMapper.MAPPER.customerToCustomerResponse(customer);
    }

    @Override
    public CustomerResponse modifyCustomer(UUID id, CustomerRequest customerRequest){
        Customer customer = getCustomerById(id);
        customer.setFirstName(customerRequest.firstName());
        customer.setLastName(customerRequest.lastName());
        customer = customerRepository.save(customer);

        return CustomerMapper.MAPPER.customerToCustomerResponse(customer);
    }

    @Override
    @Transactional
    public void attachAccount(UUID id, AttachAccountRequest attachAccountRequest) {
        Customer customer = getCustomerById(id);
        Account account = accountRepository.findById(attachAccountRequest.accountId()).orElseThrow(() -> new NotFoundException(String.format("Account %s not found ", id)));
        customer.setAccount(account);
    }


    private Customer getCustomerById(UUID id)  {
        return customerRepository.findById(id).orElseThrow(()->new NotFoundException(String.format("Customer %s not found ",id)));
    }

}
