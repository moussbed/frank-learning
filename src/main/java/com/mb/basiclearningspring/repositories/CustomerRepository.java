package com.mb.basiclearningspring.repositories;

import com.mb.basiclearningspring.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;


public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    @Query("""
        SELECT CASE WHEN COUNT(customer) > 0 THEN TRUE
        ELSE FALSE END
        FROM Customer customer WHERE customer.email = ?1
     """)
    Boolean selectExistsEmail(String email);
}
