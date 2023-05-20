package com.github.knextsunj.cms.controller;

import com.github.knextsunj.cms.dto.CustomerDTO;
import com.github.knextsunj.cms.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "/findAll")
    public List<CustomerDTO> findAllCountries() {
        return customerService.findAllCustomers();
    }

    @PostMapping(value = "/save")
    public boolean saveCountry(@RequestBody CustomerDTO customerDTO) {
        return customerService.saveCustomer(customerDTO);
    }

    @PutMapping(value = "/update")
    public boolean updateCountry(@RequestBody CustomerDTO customerDTO) {
        return customerService.updateCustomer(customerDTO);
    }

}
