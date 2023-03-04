package com.mb.basiclearningspring.services;



import com.mb.basiclearningspring.dto.AttachAccountRequest;
import com.mb.basiclearningspring.dto.CustomerRequest;
import com.mb.basiclearningspring.dto.CustomerResponse;

import java.util.List;
import java.util.UUID;

public interface CustomerService {


    List<CustomerResponse>  getCustomers();

    void addCustomer(CustomerRequest customer);

    CustomerResponse getCustomer(UUID id) ;

    CustomerResponse modifyCustomer(UUID id, CustomerRequest customerRequest) ;

    void attachAccount(UUID id,AttachAccountRequest attachAccountRequest);

}
