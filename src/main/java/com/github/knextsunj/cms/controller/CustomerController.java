package com.github.knextsunj.cms.controller;

import com.github.knextsunj.cms.annotation.JWTRequired;
import com.github.knextsunj.cms.dto.CustomerDTO;
import com.github.knextsunj.cms.service.CustomerService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Stateless
@Path("/customer")
@JWTRequired
public class CustomerController {

    @Inject
    private CustomerService customerService;

    @GET
    @Path("/findAll")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<CustomerDTO> findAllCountries() {
        return customerService.findAllCustomers();
    }

    @POST
    @Path("/save")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean saveCountry(CustomerDTO customerDTO) {
        return customerService.saveCustomer(customerDTO);
    }

    @PUT
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean updateCountry(CustomerDTO customerDTO) {
        return customerService.updateCustomer(customerDTO);
    }

}
