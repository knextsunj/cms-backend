package com.github.knextsunj.cms.controller;

import com.github.knextsunj.cms.dto.CountryDTO;
import com.github.knextsunj.cms.responsedto.CountryResponseDTO;
import com.github.knextsunj.cms.service.CountryService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Stateless
@Path("/country")
public class CountryController {

    @Inject
    private CountryService countryService;

    @GET
    @Path("/findAll")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<CountryResponseDTO> findAllCountries() {
        return countryService.findAllCountries();
    }

    @POST
    @Path("/save")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean saveCountry(CountryDTO countryDTO) {
        return countryService.saveCountry(countryDTO);
    }

    @PUT
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean updateCountry(CountryDTO countryDTO) {
        return countryService.updateCountry(countryDTO);
    }
    
    
}
