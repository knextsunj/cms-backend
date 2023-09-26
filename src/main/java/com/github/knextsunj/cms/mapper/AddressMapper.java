package com.github.knextsunj.cms.mapper;

import com.github.knextsunj.cms.domain.Address;
import com.github.knextsunj.cms.dto.AddressDTO;
import com.github.knextsunj.cms.util.MapperUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.CDI)
public interface AddressMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "deleted", source = "deleted")
    @Mapping(target = "street", source = "street")
    @Mapping(target = "locality", source = "locality")
    @Mapping(target = "area", source = "area")
    @Mapping(target = "pincode", source = "pincode")
    @Mapping(target = "cityId", source = "city.id")
    @Mapping(target = "stateId", source = "state.id")
    @Mapping(target = "countryId", source = "country.id")
    @Mapping(target = "addressTypeId", source = "addressType.id")
    @Mapping(target = "customerId", source = "customer.id")
    AddressDTO toAddressDTO(Address address);

    default Address fromAddressDTO(AddressDTO addressDTO) {
        return MapperUtil.fromAddressDTO(addressDTO);
    }

    default Address setDates(Address address) {
        return MapperUtil.setAddressDates(address);
    }
}
