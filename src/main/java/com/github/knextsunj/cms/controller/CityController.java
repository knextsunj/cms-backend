package com.github.knextsunj.cms.controller;

import com.github.knextsunj.cms.annotation.JWTRequired;
import com.github.knextsunj.cms.dto.CityDTO;
import com.github.knextsunj.cms.service.CityService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Stateless
@Path("/city")
@JWTRequired
public class CityController {

    @Inject
    private CityService cityService;

    @GET
    @Path("/find/{stateId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<CityDTO> findCitiesByState(@PathParam("stateId") Long stateId) {
        return cityService.findCitiesByStateId(stateId);
    }

    @PUT
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean updateState(CityDTO cityDTO) {
        return cityService.updateCity(cityDTO);
    }

    @POST
    @Path("/save")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean saveState(CityDTO cityDTO) {
        return cityService.saveCity(cityDTO);
    }

}
