package com.github.knextsunj.cms.controller;

import com.github.knextsunj.cms.annotation.JWTRequired;
import com.github.knextsunj.cms.dto.AddressDTO;
import com.github.knextsunj.cms.service.AddressService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Stateless
@Path("/address")
@JWTRequired
public class AddressController {

    @Inject
    private AddressService addressService;

    @POST
    @Path("/save")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean saveAddress(AddressDTO addressDTO) {
        return addressService.saveAddress(addressDTO);
    }

    @PUT
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean updateAddress(AddressDTO addressDTO) {
        return addressService.updateAddress(addressDTO);
    }

    @GET
    @Path("/fetchAll/{customerId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<AddressDTO> findAllAddress(@PathParam("customerId") Long customerId) {
        return addressService.fetchAllAddress(customerId);
    }

}
