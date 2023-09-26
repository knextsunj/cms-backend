package com.github.knextsunj.cms.controller;

import java.util.List;

import com.github.knextsunj.cms.dto.StateDTO;
import com.github.knextsunj.cms.service.StateService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Stateless
@Path("/state")
public class StateController {

	@Inject
	private StateService stateService;

	//For show all states
	@GET
	@Path("/fetchAll/{countryId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<StateDTO> findStatesByCountry(@PathParam("countryId") Long countryId) {
		return stateService.findStatesByCountryId(countryId);
	}
	
	@PUT
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean updateState(StateDTO stateDTO) {
		return stateService.updateState(stateDTO);
	}

	@POST
	@Path("/save")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean saveState(StateDTO stateDTO) {
		return stateService.saveState(stateDTO);
	}

}
