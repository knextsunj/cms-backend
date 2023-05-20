package com.github.knextsunj.cms.service;

import com.github.knextsunj.cms.dto.CityDTO;

import java.util.List;

public interface CityService {

    boolean saveCity(CityDTO cityDTO);

    boolean updateCity(CityDTO cityDTO);

    List<CityDTO> findCitiesByStateId(Long stateId);
}
