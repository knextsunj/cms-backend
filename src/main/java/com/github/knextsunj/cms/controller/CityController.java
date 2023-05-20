package com.github.knextsunj.cms.controller;

import com.github.knextsunj.cms.dto.CityDTO;
import com.github.knextsunj.cms.dto.StateDTO;
import com.github.knextsunj.cms.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/city")
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping("/find/{stateId}")
    public List<CityDTO> findCitiesByState(@PathVariable("stateId") Long stateId) {
        return cityService.findCitiesByStateId(stateId);
    }

    @PutMapping("/update")
    public boolean updateState(@RequestBody CityDTO cityDTO) {
        return cityService.updateCity(cityDTO);
    }

    @PostMapping("/save")
    public boolean saveState(@RequestBody CityDTO cityDTO) {
        return cityService.saveCity(cityDTO);
    }

}
