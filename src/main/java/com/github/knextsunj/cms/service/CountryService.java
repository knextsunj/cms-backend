package com.github.knextsunj.cms.service;

import com.github.knextsunj.cms.dto.CountryDTO;
import com.github.knextsunj.cms.responsedto.CountryResponseDTO;

import java.util.List;
import java.util.Map;

public interface CountryService {

    boolean saveCountry(CountryDTO countryDTO);

    boolean updateCountry(CountryDTO countryDTO);

    List<CountryResponseDTO> findAllCountries();

    CountryDTO fetchCountryById(long id);
}
