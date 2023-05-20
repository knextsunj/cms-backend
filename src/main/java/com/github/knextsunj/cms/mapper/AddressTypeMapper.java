package com.github.knextsunj.cms.mapper;

import com.github.knextsunj.cms.domain.AddressType;
import com.github.knextsunj.cms.domain.CustomerStatus;
import com.github.knextsunj.cms.dto.AddressTypeDTO;
import com.github.knextsunj.cms.dto.CustomerStatusDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AddressTypeMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "deleted", source = "deleted")
    AddressType fromAddressTypeDTO(AddressTypeDTO addressTypeDTO);

    AddressTypeDTO toAddressTypeDTO(AddressType addressType);

    default AddressType setDates(AddressType addressType) {
        if (Optional.ofNullable(addressType).isPresent()) {
            if (Objects.isNull(addressType.getCreatedDate())) {
                addressType.setCreatedDate(LocalDateTime.now());
            }
            addressType.setUpdatedDate(LocalDateTime.now());

        }
        return addressType;
    }

}
