package com.github.knextsunj.cms.controller;

import com.github.knextsunj.cms.dto.AddressDTO;
import com.github.knextsunj.cms.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public boolean saveAddress(@RequestBody AddressDTO addressDTO) {
        return addressService.saveAddress(addressDTO);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public boolean updateAddress(@RequestBody AddressDTO addressDTO) {
        return addressService.updateAddress(addressDTO);
    }

    @RequestMapping(value = "/fetchAll/{customerId}", method = RequestMethod.GET)
    public List<AddressDTO> findAllAddress(@PathVariable("customerId") Long customerId) {
        return addressService.fetchAllAddress(customerId);
    }

}
