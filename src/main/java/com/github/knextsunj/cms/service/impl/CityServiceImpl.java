package com.github.knextsunj.cms.service.impl;

import com.github.knextsunj.cms.domain.City;
import com.github.knextsunj.cms.domain.Country;
import com.github.knextsunj.cms.domain.State;
import com.github.knextsunj.cms.dto.CityDTO;
import com.github.knextsunj.cms.exception.BusinessException;
import com.github.knextsunj.cms.exception.DataNotFoundException;
import com.github.knextsunj.cms.exception.ValidationFailureException;
import com.github.knextsunj.cms.mapper.CityMapper;
import com.github.knextsunj.cms.repository.CityRepository;
import com.github.knextsunj.cms.repository.StateRepository;
import com.github.knextsunj.cms.service.CityService;
import com.github.knextsunj.cms.service.validation.GenericValidationService;
import com.github.knextsunj.cms.util.CmsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    @Qualifier("cityValidationService")
    private GenericValidationService genericValidationService;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CityMapper cityMapper;

    @Override
    @CacheEvict(cacheNames = "allCitiesByState",allEntries = true)
    public boolean saveCity(CityDTO cityDTO) {
        if (!CmsUtil.isNull(cityDTO) && !CmsUtil.isNull(cityDTO.name()) && CmsUtil.isNumPresent(cityDTO.stateId())) {

            if (cityRepository.count()!=0L && !genericValidationService.deDup(cityDTO.name())) {
                throw new ValidationFailureException("State with given name: " + cityDTO.name() + " already registered");
            }


            Optional<State> stateOptional = stateRepository.findById(cityDTO.stateId());
            if(stateOptional.isPresent()) {

                City newCity = cityMapper.setDates(cityMapper.fromCityDTO(cityDTO));
                newCity.setState(stateOptional.get());
                City savedCity = cityRepository.save(newCity);
                if(null!=savedCity) {
                    return true;
                }
            }
            else {
                throw new DataNotFoundException("Unable to save state with name::"+cityDTO.name()+", required country mapping");
            }
        }
        return false;
    }

    @Override
    @CacheEvict(cacheNames = "allCitiesByState",key="#cityDTO.stateId")
    public boolean updateCity(CityDTO cityDTO) {
        if (Optional.ofNullable(cityDTO).isPresent() && CmsUtil.isNumPresent(cityDTO.id())) {
            Optional<City> optionalCity = cityRepository.findById(cityDTO.id());
            if(optionalCity.isPresent()) {
                City city = optionalCity.get();
                if(!CmsUtil.isNull(cityDTO.name())){
                    city.setName(cityDTO.name());
                }
                if(!CmsUtil.isNull(cityDTO.deleted()) && cityDTO.deleted().equals("Y")) {
                    city.setDeleted(cityDTO.deleted());
                }
                city.setUpdatedDate(LocalDateTime.now());
                cityRepository.save(city);
                return true;
            }
        }
        return false;
    }

    @Override
    @Cacheable(cacheNames = "allCitiesByState",key="#stateId")
    public List<CityDTO> findCitiesByStateId(Long stateId) {
        if (Optional.ofNullable(stateId).isPresent()) {
            List<City> cities = this.cityRepository.findCityByStateIdAndDeleted(stateId,"N");

            return cities.stream().map(state -> {
                return cityMapper.toCityDTO(state);
            }).collect(Collectors.toList());
        }

        else {
            throw new BusinessException("Failed to fetch list of cities associated with state id: " + stateId);
        }

    }
}
