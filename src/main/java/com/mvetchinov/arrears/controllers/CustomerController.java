package com.mvetchinov.arrears.controllers;

import com.mvetchinov.arrears.model.Customer;
import com.mvetchinov.arrears.repositories.CustomerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    private CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getCustomers(Model model) {
        model.addAttribute("customers", customerRepository.findAll());
        return "customers";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id:[\\d]+}")
    public String getDeal(Model model, @PathVariable Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (!customer.isPresent()) throw new IllegalArgumentException("No customers was found by id " + id);
        model.addAttribute("customer", customer.get());
        return "customer";
    }
}
