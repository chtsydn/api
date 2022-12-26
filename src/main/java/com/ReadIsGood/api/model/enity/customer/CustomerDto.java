package com.ReadIsGood.api.model.enity.customer;

import lombok.Data;

@Data
public class CustomerDto {
    private Long id;
    private String name;
    private String surname;
    private Integer age;
    private String email;

    public CustomerDto(Customer customer){
        this.id=customer.getId();
        this.name=customer.getName();
        this.surname= customer.getSurname();
        this.age= customer.getAge();
        this.email= customer.getEmail();
    }
}
