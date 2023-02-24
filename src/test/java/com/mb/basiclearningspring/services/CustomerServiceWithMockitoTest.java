package com.mb.basiclearningspring.services;

import com.mb.basiclearningspring.dto.CustomerRequest;
import com.mb.basiclearningspring.dto.CustomerResponse;
import com.mb.basiclearningspring.entities.Customer;
import com.mb.basiclearningspring.repositories.CustomerRepository;
import com.mb.basiclearningspring.services.impl.CustomerServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class CustomerServiceWithMockitoTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Captor
    private ArgumentCaptor<Customer> customerArgumentCaptor;

    @Test
    public void shouldGetCustomers(){
        // Given
        when(customerRepository.findAll()).thenReturn(List.of(new Customer("Bedril","Kgrav")));
        // When
        List<CustomerResponse> customers = customerService.getCustomers();
        // Then
        assertThat(customers.size()).isEqualTo(1);
    }

    @Test
    public void shouldAddCustomer(){
        // Given
        final String firstName = "Kgrav";
        final String lastName = "Bedril";
        CustomerRequest customerRequest = new CustomerRequest(firstName, lastName);
        when(customerRepository.save(any())).thenReturn(null);
        // When
        customerService.addCustomer(customerRequest);
        verify(customerRepository).save(customerArgumentCaptor.capture());

        // Then
        Customer actual = customerArgumentCaptor.getValue();
        assertThat(actual.getFirstName()).isEqualTo(firstName);
        assertThat(actual.getLastName()).isEqualTo(lastName);


    }

}
