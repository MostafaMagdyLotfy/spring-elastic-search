package com.mostafa.elasticsearch.entity.dto;

import com.mostafa.elasticsearch.entity.model.Customer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDto {

    @NotBlank(message = "Name may not be blank")
    private String name;

    @Positive(message = "Age must be greater than 0")
    @NotNull(message = "Age may not be blank")
    private Integer age;

    @NotBlank(message = "Address may not be blank")
    private String address;

    @NotBlank(message = "Email may not be blank")
    private String email;

    public static Customer transform(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setName(customerDto.getName());
        customer.setAge(customerDto.getAge());
        customer.setAddress(customerDto.getAddress());
        customer.setEmail(customerDto.getEmail());
        return customer;
    }

}