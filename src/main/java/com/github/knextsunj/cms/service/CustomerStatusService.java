package com.github.knextsunj.cms.service;

import com.github.knextsunj.cms.dto.CustomerStatusDTO;

import java.util.List;

public interface CustomerStatusService {

    boolean saveCustomerStatus(CustomerStatusDTO customerStatusDTO);

    boolean updateCustomerStatus(CustomerStatusDTO customerStatusDTO);

    List<CustomerStatusDTO> findAllCustomerStatus();

    CustomerStatusDTO fetchCustomerStatusById(long id);
}
