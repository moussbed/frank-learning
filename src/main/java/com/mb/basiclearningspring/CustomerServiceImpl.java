package com.mb.basiclearningspring;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{

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
    public CustomerResponse getCustomer(Long id) throws NotFoundException {
        Customer customer = getCustomerById(id);
        return CustomerMapper.MAPPER.customerToCustomerResponse(customer);
    }

    @Override
    public CustomerResponse modifyCustomer(Long id, CustomerRequest customerRequest) throws NotFoundException {
        Customer customer = getCustomerById(id);
        customer.setFirstName(customerRequest.firstName());
        customer.setLastName(customerRequest.lastName());
        customer = customerRepository.save(customer);

        return CustomerMapper.MAPPER.customerToCustomerResponse(customer);
    }

    private Customer getCustomerById(Long id) throws NotFoundException {
        return customerRepository.findById(id).orElseThrow(NotFoundException::new);
    }

}
