package com.github.knextsunj.cms.service.impl;

import com.github.knextsunj.cms.domain.Country;
import com.github.knextsunj.cms.dto.CountryDTO;
import com.github.knextsunj.cms.exception.DataNotFoundException;
import com.github.knextsunj.cms.exception.ValidationFailureException;
import com.github.knextsunj.cms.mapper.CountryMapper;
import com.github.knextsunj.cms.repository.CountryRepository;
import com.github.knextsunj.cms.responsedto.CountryResponseDTO;
import com.github.knextsunj.cms.service.CountryService;
import com.github.knextsunj.cms.service.validation.GenericValidationService;
import com.github.knextsunj.cms.util.CmsExceptionUtil;
import com.github.knextsunj.cms.util.CmsUtil;
import com.github.knextsunj.cms.util.SerialNumberCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    @Qualifier("countryValidationService")
    private GenericValidationService genericValidationService;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CountryMapper countryMapper;

    private static ThreadLocal<Long> serialNumberThreadLocal = new ThreadLocal<>();

    @Override
    @CacheEvict(cacheNames="allCountries", allEntries=true)
    public boolean saveCountry(CountryDTO countryDTO) {

        if (!CmsUtil.isNull(countryDTO) && !CmsUtil.isNull(countryDTO.name())) {
            if (countryRepository.count()!=0L && !genericValidationService.deDup(countryDTO.name())) {
                throw new ValidationFailureException("Country with given name: " + countryDTO.name() + " already registered");
            }

            Country savedCountry = countryRepository.save(countryMapper.setDates(countryMapper.fromCountryDTO(countryDTO)));
            if (!CmsUtil.isNull(savedCountry)) {
                return true;
            }
        }
        return false;
    }

    @Override
//    @Caching(put= {@CachePut(cacheNames ="allCountries",key="#countryDTO.id")},
//            evict={@CacheEvict(cacheNames = "allCountries",key="#countryDTO.id",condition = "#countryDTO.deleted=='Y'")})
    @CacheEvict(cacheNames="allCountries", allEntries=true)
    public boolean updateCountry(CountryDTO countryDTO) {
        if (Optional.ofNullable(countryDTO).isPresent() && CmsUtil.isNumPresent(countryDTO.id())) {
            Optional<Country> countryOptional = countryRepository.findById(countryDTO.id());
            if (countryOptional.isPresent()) {
                Country country = countryOptional.get();
                if (!CmsUtil.isNull(countryDTO.deleted()) && countryDTO.deleted().equals("Y")) {
                    country.setDeleted("Y");
                }
                if(!CmsUtil.isNull(countryDTO.name()))
                country.setName(countryDTO.name());
                Country updatedCountry = countryRepository.save(countryMapper.setDates(country));
                if (updatedCountry != null)
                    return true;
            }
        }
        return false;
    }

    @Override
    @Cacheable(cacheNames ="allCountries")
    public List<CountryResponseDTO> findAllCountries() {
        serialNumberThreadLocal.set(0L);
        List<CountryResponseDTO> countryResponseDTOList =
                countryRepository.findAllCountriesByDeleted("N").stream().map(country -> {
                    return countryMapper.buildCountryResponseDTO(countryMapper.toCountryDTO(country), SerialNumberCalculator.calculateAndGiveSerialNo(serialNumberThreadLocal).get());
                }).collect(Collectors.toList());
        return countryResponseDTOList;

    }

    @Override
    public CountryDTO fetchCountryById(long id) {
        Optional<Country> countryOptional = countryRepository.findById(id);
        if (countryOptional.isPresent()) {
            return countryMapper.toCountryDTO(countryOptional.get());
        } else {
            throw new DataNotFoundException(CmsExceptionUtil.composeExceptionMessage("No country data found for id: ", id));
        }
    }

  }
