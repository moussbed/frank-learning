package com.mb.basiclearningspring.services;



import com.mb.basiclearningspring.dto.CustomerRequest;
import com.mb.basiclearningspring.dto.CustomerResponse;

import java.util.List;

public interface CustomerService {


    List<CustomerResponse>  getCustomers();

    void addCustomer(CustomerRequest customer);

    CustomerResponse getCustomer(Long id) ;

    CustomerResponse modifyCustomer(Long id, CustomerRequest customerRequest) ;
}
