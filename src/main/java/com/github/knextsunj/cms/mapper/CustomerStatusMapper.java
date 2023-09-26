package com.github.knextsunj.cms.mapper;

import com.github.knextsunj.cms.domain.Country;
import com.github.knextsunj.cms.domain.Customer;
import com.github.knextsunj.cms.domain.CustomerStatus;
import com.github.knextsunj.cms.dto.CustomerStatusDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Mapper(componentModel = MappingConstants.ComponentModel.CDI)
public interface CustomerStatusMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "deleted", source = "deleted")
    CustomerStatus fromCustomerStatusDTO(CustomerStatusDTO customerStatusDTO);

    CustomerStatusDTO toCustomerStatusDTO(CustomerStatus customerStatus);

    default CustomerStatus setDates(CustomerStatus customerStatus) {
        if (Optional.ofNullable(customerStatus).isPresent()) {
            if (Objects.isNull(customerStatus.getCreatedDate())) {
                customerStatus.setCreatedDate(LocalDateTime.now());
            }
            customerStatus.setUpdatedDate(LocalDateTime.now());

        }
        return customerStatus;
    }
}
