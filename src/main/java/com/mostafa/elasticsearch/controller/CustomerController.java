package com.mostafa.elasticsearch.controller;

import com.mostafa.elasticsearch.entity.dto.CustomerDto;
import com.mostafa.elasticsearch.entity.model.Customer;
import com.mostafa.elasticsearch.service.CustomerService;
import com.mostafa.elasticsearch.service.exception.CustomerNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAll();
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping
    public Customer createCustomer(@Valid @RequestBody CustomerDto customer) {
        return customerService.create(CustomerDto.transform(customer));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}")
    public Customer getCustomerById(@PathVariable String id) throws CustomerNotFoundException {
        return customerService.getById(id).orElseThrow(() -> new CustomerNotFoundException("The given id is invalid"));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/query")
    public List<Customer> getCustomersByNameAndAge(@RequestParam(value = "name") String name, @RequestParam(value = "age") Integer age) {
        return customerService.findByNameAndAge(name, age);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{id}")
    public Customer updateCustomer(@PathVariable String id, @RequestBody CustomerDto customer) throws CustomerNotFoundException {
        return customerService.update(id, CustomerDto.transform(customer));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable String id) throws CustomerNotFoundException {
        customerService.deleteById(id);
        return ResponseEntity.ok().build();
    }


}
