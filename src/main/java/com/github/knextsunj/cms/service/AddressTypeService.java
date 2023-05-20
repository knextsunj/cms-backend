package com.github.knextsunj.cms.service;

import com.github.knextsunj.cms.dto.AddressTypeDTO;

import java.util.List;

public interface AddressTypeService {

    boolean saveAddressType(AddressTypeDTO addressTypeDTO);

    boolean updateAddressType(AddressTypeDTO addressTypeDTO);

    List<AddressTypeDTO> findAllAddressType();

    AddressTypeDTO fetchAddressTypeById(long id);
}
