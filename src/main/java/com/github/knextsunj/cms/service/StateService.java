package com.github.knextsunj.cms.service;

import java.util.List;

import com.github.knextsunj.cms.dto.StateDTO;

public interface StateService {

	boolean saveState(StateDTO stateDTO);

	boolean updateState(StateDTO stateDTO);

	List<StateDTO> findStatesByCountryId(Long countryId);

}
