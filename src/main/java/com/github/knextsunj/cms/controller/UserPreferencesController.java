package com.github.knextsunj.cms.controller;

import com.github.knextsunj.cms.dto.UserPreferenceDTO;
import com.github.knextsunj.cms.service.UserPreferenceService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Stateless
@Path("/userpreferences")
public class UserPreferencesController {

    @Inject
    private UserPreferenceService userPreferenceService;

    @GET
    @Path("/fetchUserPreferences/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public UserPreferenceDTO fetchUserPreferences(@PathParam("username") String username) {
        return userPreferenceService.retrieveUserPreferences(username);
    }
}


