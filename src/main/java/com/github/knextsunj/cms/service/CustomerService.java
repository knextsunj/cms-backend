package com.github.knextsunj.cms.service;

import com.github.knextsunj.cms.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {

    boolean saveCustomer(CustomerDTO customerDTO,String authHeader);

    boolean updateCustomer(CustomerDTO customerDTO,String authHeader);

    List<CustomerDTO> findAllCustomers();

    CustomerDTO findCustomerById(Long id);
}
