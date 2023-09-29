package com.github.knextsunj.cms.service.impl;

import com.github.knextsunj.cms.annotation.CityValidationService;
import com.github.knextsunj.cms.domain.City;
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
//import org.springframework.beans.factory.annotation.Qualifier;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Stateless
@Local(CityService.class)
public class CityServiceImpl implements CityService {

    @Inject
    @CityValidationService
    private GenericValidationService genericValidationService;

    @Inject
    private StateRepository stateRepository;

    @Inject
    private CityRepository cityRepository;

    @Inject
    private CityMapper cityMapper;

    @Override
    public boolean saveCity(CityDTO cityDTO) {
        if (!CmsUtil.isNull(cityDTO) && !CmsUtil.isNull(cityDTO.getName()) && CmsUtil.isNumPresent(cityDTO.getStateId())) {

            if (cityRepository.count()!=0L && !genericValidationService.deDup(cityDTO.getName())) {
                throw new ValidationFailureException("State with given name: " + cityDTO.getName() + " already registered");
            }


            Optional<State> stateOptional = stateRepository.findOptionalBy(cityDTO.getStateId());
            if(stateOptional.isPresent()) {

                City newCity = cityMapper.setDates(cityMapper.fromCityDTO(cityDTO));
                newCity.setState(stateOptional.get());
                City savedCity = cityRepository.save(newCity);
                if(null!=savedCity) {
                    return true;
                }
            }
            else {
                throw new DataNotFoundException("Unable to save state with name::"+cityDTO.getName()+", required country mapping");
            }
        }
        return false;
    }

    @Override
    public boolean updateCity(CityDTO cityDTO) {
        if (Optional.ofNullable(cityDTO).isPresent() && CmsUtil.isNumPresent(cityDTO.getId())) {
            Optional<City> optionalCity = cityRepository.findOptionalBy(cityDTO.getId());
            if(optionalCity.isPresent()) {
                City city = optionalCity.get();
                if(!CmsUtil.isNull(cityDTO.getName())){
                    city.setName(cityDTO.getName());
                }
                if(!CmsUtil.isNull(cityDTO.getDeleted()) && cityDTO.getDeleted().equals("Y")) {
                    city.setDeleted(cityDTO.getDeleted());
                }
                city.setUpdatedDate(LocalDateTime.now());
                cityRepository.save(city);
                return true;
            }
        }
        return false;
    }

    @Override
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
