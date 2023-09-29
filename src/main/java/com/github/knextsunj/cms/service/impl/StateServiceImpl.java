package com.github.knextsunj.cms.service.impl;

import com.github.knextsunj.cms.annotation.StateValidationService;
import com.github.knextsunj.cms.domain.Country;
import com.github.knextsunj.cms.domain.State;
import com.github.knextsunj.cms.dto.StateDTO;
import com.github.knextsunj.cms.exception.BusinessException;
import com.github.knextsunj.cms.exception.DataNotFoundException;
import com.github.knextsunj.cms.exception.ValidationFailureException;
import com.github.knextsunj.cms.mapper.StateMapper;
import com.github.knextsunj.cms.repository.CountryRepository;
import com.github.knextsunj.cms.repository.StateRepository;
import com.github.knextsunj.cms.service.StateService;
import com.github.knextsunj.cms.service.validation.GenericValidationService;
import com.github.knextsunj.cms.util.CmsUtil;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Stateless
@Local(StateService.class)
public class StateServiceImpl implements StateService {

	@Inject
	private StateRepository stateRepository;

	@Inject
	private CountryRepository countryRepository;

	@Inject
	private StateMapper stateMapper;

	@Inject
	@StateValidationService
	private GenericValidationService genericValidationService;

	@Override
	public boolean saveState(StateDTO stateDTO) {
		if (!CmsUtil.isNull(stateDTO) && !CmsUtil.isNull(stateDTO.getName()) && CmsUtil.isNumPresent(stateDTO.getCountryId())) {



			if (stateRepository.count()!=0L && !genericValidationService.deDup(stateDTO.getName())) {
				throw new ValidationFailureException("State with given name: " + stateDTO.getName() + " already registered");
			}


		Optional<Country> countryOptional = countryRepository.findOptionalBy(stateDTO.getCountryId());
			if(countryOptional.isPresent()) {

			State newState = stateMapper.setDates(stateMapper.fromStateDTO(stateDTO));
			newState.setCountry(countryOptional.get());
			State savedState = stateRepository.save(newState);
			if(null!=savedState) {
				return true;
			}
		}
		else {
			throw new DataNotFoundException("Unable to save state with name::"+stateDTO.getDeleted()+", required country mapping");
		}
		}
		return false;
	}

	@Override
	public boolean updateState(StateDTO stateDTO) {
		if (Optional.ofNullable(stateDTO).isPresent() && CmsUtil.isNumPresent(stateDTO.getId())) {
			Optional<State> optionalState = stateRepository.findOptionalBy(stateDTO.getId());
			if(optionalState.isPresent()) {
				State state = optionalState.get();
				if(!CmsUtil.isNull(stateDTO.getName())) {
					state.setName(stateDTO.getName());
				}
				if(!CmsUtil.isNull(stateDTO.getDeleted()) && stateDTO.getDeleted().equals("Y")) {
					state.setDeleted(stateDTO.getDeleted());
				}
				state.setUpdatedDate(LocalDateTime.now());
				stateRepository.save(state);
				return true;
			}
		}
		return false;
	}

	@Override
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
