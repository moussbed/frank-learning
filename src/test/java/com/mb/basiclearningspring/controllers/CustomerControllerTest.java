package com.mb.basiclearningspring.controllers;

import com.mb.basiclearningspring.dto.CustomerResponse;
import com.mb.basiclearningspring.services.CustomerService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @MockBean
    CustomerService customerService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getCustomers() throws Exception {
        // Given
        LocalDateTime now = LocalDateTime.now();
        when(customerService.getCustomers()).thenReturn(List.of(
                new CustomerResponse (UUID.randomUUID().toString(),"Henry", "Roland", now, now),
                new CustomerResponse (UUID.randomUUID().toString(),"Arnold", "Vincent", now.minusDays(2), now.plusHours(3))
        ));

        // When
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v0/customers"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].firstName", Matchers.is("Henry")))
                .andExpect(jsonPath("$[0].lastName", Matchers.is("Roland")))
                .andExpect(jsonPath("$[0].createdAt", Matchers.is(now.toString())))
                .andExpect(jsonPath("$[0].modifiedAt", Matchers.is(now.toString())));
    }

    @Test
    void addCustomer() {
    }

    @Test
    void getCustomer() {
    }

    @Test
    void modifyCustomer() {
    }
}