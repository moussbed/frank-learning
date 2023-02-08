package com.mb.basiclearningspring.repositories;

import com.mb.basiclearningspring.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
