package com.github.knextsunj.cms.mapper;

import com.github.knextsunj.cms.builder.CountryResponseDTOBuilder;
import com.github.knextsunj.cms.domain.Country;
import com.github.knextsunj.cms.dto.CountryDTO;
import com.github.knextsunj.cms.responsedto.CountryResponseDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.time.LocalDateTime;
import java.util.Optional;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CountryMapper {


    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "deleted", source = "deleted")
    Country fromCountryDTO(CountryDTO countryDTO);

    @InheritInverseConfiguration
    CountryDTO toCountryDTO(Country country);

    default Country setDates(Country country) {
        if (Optional.ofNullable(country).isPresent()) {
            if (Optional.ofNullable(country.getCreatedDate()).isEmpty()) {
                country.setCreatedDate(LocalDateTime.now());
            }
            country.setUpdatedDate(LocalDateTime.now());

        }
        return country;
    }

    default CountryResponseDTO buildCountryResponseDTO(CountryDTO countryDTO, Long serialNo) {
        return CountryResponseDTOBuilder.countryResponseDTOBuilder().setId(countryDTO.id()).setName(countryDTO.name())
                .setDeleted(countryDTO.deleted()).setSerialNo(serialNo).build();
    }

}
