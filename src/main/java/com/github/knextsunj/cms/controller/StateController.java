package com.github.knextsunj.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.github.knextsunj.cms.dto.StateDTO;
import com.github.knextsunj.cms.service.StateService;

@RestController
@RequestMapping("/state")
public class StateController {

	@Autowired
	private StateService stateService;

	//For show all states
	@GetMapping("/find/{countryId}")
	public List<StateDTO> findStatesByCountry(@PathVariable("countryId") Long countryId) {
		return stateService.findStatesByCountryId(countryId);
	}
	
	@PutMapping("/update")
	public boolean updateState(@RequestBody StateDTO stateDTO) {
		return stateService.updateState(stateDTO);
	}

	@PostMapping("/save")
	public boolean saveState(@RequestBody StateDTO stateDTO) {
		return stateService.saveState(stateDTO);
	}

}
