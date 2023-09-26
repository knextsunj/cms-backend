package com.github.knextsunj.cms.controller;

import com.github.knextsunj.cms.dto.CustomerStatusDTO;
import com.github.knextsunj.cms.service.CustomerStatusService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Stateless
@Path("/customerstatus")
public class CustomerStatusController {

    @Inject
    private CustomerStatusService customerStatusService;

    @GET
    @Path("/findAll")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<CustomerStatusDTO> findAllCountries() {
        return customerStatusService.findAllCustomerStatus();
    }

    @POST
    @Path("/save")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean saveCustomerStatus(CustomerStatusDTO customerStatusDTO) {
        return customerStatusService.saveCustomerStatus(customerStatusDTO);
    }

    @PUT
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean updateCustomerStatus(CustomerStatusDTO customerStatusDTO) {
        return customerStatusService.updateCustomerStatus(customerStatusDTO);
    }


}
