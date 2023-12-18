package com.mostafa.elasticsearch.service.impl;

import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import com.mostafa.elasticsearch.entity.model.Customer;
import com.mostafa.elasticsearch.repository.CustomerRepository;
import com.mostafa.elasticsearch.service.CustomerService;
import com.mostafa.elasticsearch.service.exception.CustomerNotFoundException;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders.match;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final ElasticsearchTemplate elasticsearchTemplate;

    public CustomerServiceImpl(CustomerRepository customerRepository, ElasticsearchTemplate elasticsearchTemplate) {
        this.customerRepository = customerRepository;
        this.elasticsearchTemplate = elasticsearchTemplate;
    }

    @Override
    public Optional<Customer> getById(String id) {
        return customerRepository.findById(id);
    }

    @Override
    public List<Customer> getAll() {
        List<Customer> customers = new ArrayList<>();
        customerRepository.findAll()
                .forEach(customers::add);
        return customers;
    }

    @Override
    public List<Customer> findByNameAndAge(String name, Integer age) {
        var criteria = QueryBuilders.bool(builder -> builder.must(
                match(queryAuthor -> queryAuthor.field("name").query(name)),
                match(queryTitle -> queryTitle.field("age").query(age))
        ));

        return elasticsearchTemplate.search(NativeQuery.builder().withQuery(criteria).build(), Customer.class)
                .stream().map(SearchHit::getContent).toList();
    }

    @Override
    public Customer create(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void deleteById(String id) throws CustomerNotFoundException{
        customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("There is not customer associated with the given id"));
        customerRepository.deleteById(id);
    }

    @Override
    public Customer update(String id, Customer customer) throws CustomerNotFoundException {
        Customer oldCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("There is not customer associated with the given id"));
        oldCustomer.setName(customer.getName());
        oldCustomer.setEmail(customer.getEmail());
        oldCustomer.setAddress(customer.getAddress());
        oldCustomer.setAge(customer.getAge());
        return customerRepository.save(oldCustomer);
    }
}
