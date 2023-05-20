package com.github.knextsunj.cms.controller;

import com.github.knextsunj.cms.dto.AddressTypeDTO;
import com.github.knextsunj.cms.service.AddressTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addresstype")
public class AddressTypeController {

    @Autowired
    private AddressTypeService addressTypeService;

    @GetMapping(value = "/findAll")
    public List<AddressTypeDTO> findAllCountries() {
        return addressTypeService.findAllAddressType();
    }

    @PostMapping(value = "/save")
    public boolean saveCustomerStatus(@RequestBody AddressTypeDTO addressTypeDTO) {
        return addressTypeService.saveAddressType(addressTypeDTO);
    }

    @PutMapping(value = "/update")
    public boolean updateCustomerStatus(@RequestBody AddressTypeDTO addressTypeDTO) {
        return addressTypeService.updateAddressType(addressTypeDTO);
    }

}
