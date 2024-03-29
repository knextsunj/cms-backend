package com.github.knextsunj.cms.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import com.github.knextsunj.cms.domain.Country;
import com.github.knextsunj.cms.exception.DataNotFoundException;
import com.github.knextsunj.cms.exception.ValidationFailureException;
import com.github.knextsunj.cms.repository.CountryRepository;
import com.github.knextsunj.cms.service.validation.GenericValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.github.knextsunj.cms.domain.State;
import com.github.knextsunj.cms.dto.StateDTO;
import com.github.knextsunj.cms.exception.BusinessException;
import com.github.knextsunj.cms.mapper.StateMapper;
import com.github.knextsunj.cms.repository.StateRepository;
import com.github.knextsunj.cms.service.StateService;
import com.github.knextsunj.cms.util.CmsUtil;

@Service
public class StateServiceImpl implements StateService {

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private CountryRepository countryRepository;

	@Autowired
	private StateMapper stateMapper;

	@Autowired
	@Qualifier("stateValidationService")
	private GenericValidationService genericValidationService;

	@Override
	@CacheEvict(cacheNames = "allStatesByCountry",allEntries = true)
	public boolean saveState(StateDTO stateDTO) {
		if (!CmsUtil.isNull(stateDTO) && !CmsUtil.isNull(stateDTO.name()) && CmsUtil.isNumPresent(stateDTO.countryId())) {



			if (stateRepository.count()!=0L && !genericValidationService.deDup(stateDTO.name())) {
				throw new ValidationFailureException("State with given name: " + stateDTO.name() + " already registered");
			}


		Optional<Country> countryOptional = countryRepository.findById(stateDTO.countryId());
		if(countryOptional.isPresent()) {

			State newState = stateMapper.setDates(stateMapper.fromStateDTO(stateDTO));
			newState.setCountry(countryOptional.get());
			State savedState = stateRepository.save(newState);
			if(null!=savedState) {
				return true;
			}
		}
		else {
			throw new DataNotFoundException("Unable to save state with name::"+stateDTO.name()+", required country mapping");
		}
		}
		return false;
	}

	@Override
	@CacheEvict(cacheNames = "allStatesByCountry",key="#stateDTO.countryId")
	public boolean updateState(StateDTO stateDTO) {
		if (Optional.ofNullable(stateDTO).isPresent() && CmsUtil.isNumPresent(stateDTO.id())) {
			Optional<State> optionalState = stateRepository.findById(stateDTO.id());
			if(optionalState.isPresent()) {
				State state = optionalState.get();
				if(!CmsUtil.isNull(stateDTO.name())) {
					state.setName(stateDTO.name());
				}
				if(!CmsUtil.isNull(stateDTO.deleted()) && stateDTO.deleted().equals("Y")) {
					state.setDeleted(stateDTO.deleted());
				}
				state.setUpdatedDate(LocalDateTime.now());
				stateRepository.save(state);
				return true;
			}
		}
		return false;
	}

	@Override
	@Cacheable(cacheNames = "allStatesByCountry",key="#countryId")
	public List<StateDTO> findStatesByCountryId(Long countryId) {
		if (Optional.ofNullable(countryId).isPresent()) {
			List<State> states = this.stateRepository.findStateByCountryIdAndDeleted(countryId,"N");

			return states.stream().map(state -> {
				return stateMapper.toStateDTO(state);
			}).collect(Collectors.toList());
		}

		else {
			throw new BusinessException("Failed to fetch list of states associated with country id: " + countryId);
		}
	}

}
