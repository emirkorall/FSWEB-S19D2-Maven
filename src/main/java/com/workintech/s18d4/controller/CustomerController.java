package com.workintech.s18d4.controller;


import com.workintech.s18d4.dto.CustomerResponse;
import com.workintech.s18d4.entity.Customer;
import com.workintech.s18d4.service.CustomerService;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public List<Customer> findAll() {
        return customerService.findAll();
    }

    @GetMapping("/{id}")

    public Customer getCustomerById(@PathVariable Long id) {
        return customerService.find(id);
    }

    @PostMapping
    public CustomerResponse save(@RequestBody Customer customer) {
        Customer savedCustomer = customerService.save(customer);
        return new CustomerResponse(savedCustomer.getId(), savedCustomer.getEmail(), savedCustomer.getSalary());
    }


    @PutMapping("/{customerId}")

    public CustomerResponse update(@PathVariable("customerId") long customerID, @RequestBody Customer customer) {

        Customer updatedCustomer = customerService.find(customerID);
        if (customer == null) {
            throw new RuntimeException("Customer not found");
        }

        return new CustomerResponse(updatedCustomer.getId(), updatedCustomer.getEmail(), updatedCustomer.getSalary());

    }

    @DeleteMapping("/{id}")
    public CustomerResponse delete(@PathVariable long id) {
        Customer customer = customerService.find(id);
        if (customer == null) {
            throw new RuntimeException("Customer not found");
        }
        customerService.delete(id);

        return new CustomerResponse(customer.getId(), customer.getEmail(), customer.getSalary());

    }


}
