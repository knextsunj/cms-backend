package com.github.knextsunj.cms.controller;

import com.github.knextsunj.cms.dto.AddressTypeDTO;
import com.github.knextsunj.cms.service.AddressTypeService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Stateless
@Path("/addresstype")
public class AddressTypeController {

    @Inject
    private AddressTypeService addressTypeService;

    @GET
    @Path("/findAll")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<AddressTypeDTO> findAllCountries() {
        return addressTypeService.findAllAddressType();
    }

    @POST
    @Path("/save")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean saveCustomerStatus(AddressTypeDTO addressTypeDTO) {
        return addressTypeService.saveAddressType(addressTypeDTO);
    }

    @PUT
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean updateCustomerStatus(AddressTypeDTO addressTypeDTO) {
        return addressTypeService.updateAddressType(addressTypeDTO);
    }
}
