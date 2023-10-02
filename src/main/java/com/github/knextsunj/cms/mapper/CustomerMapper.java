package com.github.knextsunj.cms.mapper;

import com.github.knextsunj.cms.domain.Customer;
import com.github.knextsunj.cms.dto.CustomerDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Mapper(componentModel = MappingConstants.ComponentModel.CDI)
public interface CustomerMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "deleted", source = "deleted")
    @Mapping(target = "dob", source = "dateOfBirth")
    @Mapping(target = "gender", source = "gender")
    @Mapping(target = "mobileNo", source = "mobileNo")
    @Mapping(target = "emailAddress", source = "emailAddress")
    @Mapping(target="customerStatusDescr", source="customerStatus.name")
    CustomerDTO toCustomerDTO(Customer customer);

    @InheritInverseConfiguration
    Customer fromCustomerDTO(CustomerDTO customerDTO);

    default Customer setDates(Customer customer) {
        if (Optional.ofNullable(customer).isPresent()) {
            if (Objects.isNull(customer.getCreatedDate())) {
                customer.setCreatedDate(LocalDateTime.now());
            }
            customer.setUpdatedDate(LocalDateTime.now());

        }
        return customer;
    }
}
