package com.github.knextsunj.cms.controller;

import com.github.knextsunj.cms.dto.CountryDTO;
import com.github.knextsunj.cms.responsedto.CountryResponseDTO;
import com.github.knextsunj.cms.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/country")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping(value = "/findAll")
    public List<CountryResponseDTO> findAllCountries() {
        return countryService.findAllCountries();
    }

    @PostMapping(value = "/save")
    public boolean saveCountry(@RequestBody CountryDTO countryDTO) {
        return countryService.saveCountry(countryDTO);
    }

    @PutMapping(value = "/update")
    public boolean updateCountry(@RequestBody CountryDTO countryDTO) {
        return countryService.updateCountry(countryDTO);
    }
    
    
}
