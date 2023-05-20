package com.github.knextsunj.cms.service;

import com.github.knextsunj.cms.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {

    boolean saveCustomer(CustomerDTO customerDTO);

    boolean updateCustomer(CustomerDTO customerDTO);

    List<CustomerDTO> findAllCustomers();

    CustomerDTO findCustomerById(Long id);
}
