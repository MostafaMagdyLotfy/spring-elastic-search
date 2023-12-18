package com.mostafa.elasticsearch.service;

import com.mostafa.elasticsearch.entity.model.Customer;
import com.mostafa.elasticsearch.service.exception.CustomerNotFoundException;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    Optional<Customer> getById(String id);

    List<Customer> getAll();

    List<Customer> findByNameAndAge(String name, Integer age);

    Customer create(Customer customer) ;

    void deleteById(String id) throws CustomerNotFoundException;

    Customer update(String id, Customer customer) throws CustomerNotFoundException;
}
