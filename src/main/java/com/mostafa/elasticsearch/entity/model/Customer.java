package com.mostafa.elasticsearch.entity.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
@Getter
@Setter
@Document(indexName = "customers")
public class Customer {
    @Id
    private String id;
    private String name;
    private int age;
    private String address;
    private String email;
}
