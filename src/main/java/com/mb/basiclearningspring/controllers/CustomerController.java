package com.mb.basiclearningspring.controllers;

import com.mb.basiclearningspring.dto.CustomerRequest;
import com.mb.basiclearningspring.dto.CustomerResponse;
import com.mb.basiclearningspring.services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v0/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<CustomerResponse>  getCustomers(){
        return customerService.getCustomers();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addCustomer(@RequestBody @Valid CustomerRequest customer){
        customerService.addCustomer(customer);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public CustomerResponse getCustomer(@PathVariable("id") Long id)  {
        return customerService.getCustomer(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public CustomerResponse modifyCustomer(@PathVariable("id") Long id, @RequestBody CustomerRequest customer) {
        return customerService.modifyCustomer(id,customer);
    }

}
