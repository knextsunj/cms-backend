package com.github.knextsunj.cms.service;

import com.github.knextsunj.cms.dto.AddressDTO;

import java.util.List;

public interface AddressService {

    boolean saveAddress(AddressDTO addressDTO);

    boolean updateAddress(AddressDTO addressDTO);

    List<AddressDTO> fetchAllAddress(Long customerId);

}
