package com.github.knextsunj.cms.controller;

import com.github.knextsunj.cms.dto.AddressDTO;

import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Stateless
@Path("/hello")
public class HelloController {

    @GET
//    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String findAllAddress() {
        return "hello";
    }
}
