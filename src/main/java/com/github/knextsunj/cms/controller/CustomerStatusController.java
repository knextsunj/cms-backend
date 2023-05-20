package com.github.knextsunj.cms.controller;

import com.github.knextsunj.cms.dto.CustomerStatusDTO;
import com.github.knextsunj.cms.service.CustomerStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customerstatus")
public class CustomerStatusController {

    @Autowired
    private CustomerStatusService customerStatusService;

    @GetMapping(value = "/findAll")
    public List<CustomerStatusDTO> findAllCountries() {
        return customerStatusService.findAllCustomerStatus();
    }

    @PostMapping(value = "/save")
    public boolean saveCustomerStatus(@RequestBody CustomerStatusDTO customerStatusDTO) {
        return customerStatusService.saveCustomerStatus(customerStatusDTO);
    }

    @PutMapping(value = "/update")
    public boolean updateCustomerStatus(@RequestBody CustomerStatusDTO customerStatusDTO) {
        return customerStatusService.updateCustomerStatus(customerStatusDTO);
    }


}
