package com.rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.domain.Category;
import com.rest.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
