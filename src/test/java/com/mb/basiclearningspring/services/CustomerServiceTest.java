package com.mb.basiclearningspring.services;

import com.mb.basiclearningspring.dto.CustomerRequest;
import com.mb.basiclearningspring.dto.CustomerResponse;
import com.mb.basiclearningspring.entities.Customer;
import com.mb.basiclearningspring.repositories.CustomerRepository;
import com.mb.basiclearningspring.services.impl.CustomerServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static  org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class CustomerServiceTest {

    CustomerRepository customerRepository = mock(CustomerRepository.class);


    @Test
    public void shouldGetCustomers(){
         // Given
        when(customerRepository.findAll()).thenReturn(List.of(new Customer("Bedril","Kgrav")));
        CustomerService customerService = new CustomerServiceImpl(customerRepository);
        // When
        List<CustomerResponse> customers = customerService.getCustomers();
        // Then
        assertThat(customers.size()).isEqualTo(1);
    }

    @Test
    public void shouldAddCustomer(){
        // Given
        String firstName = "Kgrav";
        String lastName = "Bedril";
        CustomerRequest customerRequest = new CustomerRequest(firstName, lastName);
        when(customerRepository.save(any())).thenReturn(null);
        CustomerService customerService = new CustomerServiceImpl(customerRepository);
        // When
        customerService.addCustomer(customerRequest);
        ArgumentCaptor<Customer> customerArgumentCaptor = ArgumentCaptor.forClass(Customer.class);
        verify(customerRepository).save(customerArgumentCaptor.capture());

        // Then
        Customer actual = customerArgumentCaptor.getValue();
        assertThat(actual.getFirstName()).isEqualTo(firstName);
        assertThat(actual.getLastName()).isEqualTo(lastName);


    }
}
