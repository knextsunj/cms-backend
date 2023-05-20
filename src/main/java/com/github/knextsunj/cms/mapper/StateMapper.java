package com.github.knextsunj.cms.mapper;

import com.github.knextsunj.cms.domain.Country;
import com.github.knextsunj.cms.util.CmsUtil;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.github.knextsunj.cms.domain.State;
import com.github.knextsunj.cms.dto.StateDTO;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class StateMapper {


	public State fromStateDTO(StateDTO stateDTO) {
		State state = new State();
		state.setName(stateDTO.name());
		if(!CmsUtil.isNull(stateDTO.deleted())) {
			state.setDeleted(stateDTO.deleted());
		}
		return state;
	}


	public StateDTO toStateDTO(State state) {
		Long countryId=null;
		if(Optional.ofNullable(state.getCountry()).isPresent()) {
			countryId = state.getCountry().getId();
		}
		return new StateDTO(state.getId(), state.getName(),state.getDeleted(),countryId,state.getCountry().getName());
	}

	public State setDates(State state) {
		if (Optional.ofNullable(state).isPresent()) {
			if (Optional.ofNullable(state.getCreatedDate()).isEmpty()) {
				state.setCreatedDate(LocalDateTime.now());
			}
			state.setUpdatedDate(LocalDateTime.now());

		}
		return state;
	}
}
