package com.github.knextsunj.cms.mapper;

import com.github.knextsunj.cms.domain.City;
import com.github.knextsunj.cms.dto.CityDTO;
import com.github.knextsunj.cms.util.CmsUtil;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Mapper(componentModel = MappingConstants.ComponentModel.CDI)
public abstract class CityMapper {

    public City fromCityDTO(CityDTO cityDTO) {
        City city = new City();
        city.setName(cityDTO.getName());
        if(!CmsUtil.isNull(cityDTO.getDeleted())) {
            city.setDeleted(cityDTO.getDeleted());
        }
        return city;
    }


    public CityDTO toCityDTO(City city) {
        Long stateId=null;
        if(Optional.ofNullable(city.getState()).isPresent()) {
            stateId = city.getState().getId();
        }
        return new CityDTO(city.getId(), city.getName(),city.getDeleted(),stateId,city.getState().getName());
    }

    public City setDates(City city) {
        if (Optional.ofNullable(city).isPresent()) {
            if (Objects.isNull(city.getCreatedDate())) {
                city.setCreatedDate(LocalDateTime.now());
            }
            city.setUpdatedDate(LocalDateTime.now());

        }
        return city;
    }
}
