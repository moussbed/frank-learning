package com.mb.basiclearningspring;



import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface CustomerService {


    List<CustomerResponse>  getCustomers();

    void addCustomer(CustomerRequest customer);

    CustomerResponse getCustomer(Long id) throws ChangeSetPersister.NotFoundException;

    CustomerResponse modifyCustomer(Long id, CustomerRequest customerRequest) throws ChangeSetPersister.NotFoundException;
}
