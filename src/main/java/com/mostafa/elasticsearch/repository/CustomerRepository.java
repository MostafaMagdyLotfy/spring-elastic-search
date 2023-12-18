package com.mostafa.elasticsearch.repository;

import com.mostafa.elasticsearch.entity.model.Customer;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends ElasticsearchRepository<Customer, String> {

}