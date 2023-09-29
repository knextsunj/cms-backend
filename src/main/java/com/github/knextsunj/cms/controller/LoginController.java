package com.github.knextsunj.cms.controller;

import com.github.knextsunj.cms.config.security.TokenIssuer;
import com.github.knextsunj.cms.domain.AccountCredentials;
import com.github.knextsunj.cms.dto.AuthenticatedUser;
import com.github.knextsunj.cms.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static com.github.knextsunj.cms.config.security.Constants.AUTHORIZATION_HEADER;
import static com.github.knextsunj.cms.config.security.Constants.BEARER;


@Stateless
@Path("/")
public class LoginController {

    private static final Logger LOGGER = LogManager.getLogger(LoginController.class.getName());

    @Inject
    private TokenIssuer issuer;

    @Inject
    private UserService userService;


    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response doLogin(AccountCredentials accountCredentials) {

        LOGGER.info("login");
        AuthenticatedUser authenticatedUser = userService.validateUser(accountCredentials);

        String jwtToken = issuer.issueToken(accountCredentials.getUsername());
        return Response.ok(authenticatedUser).header(AUTHORIZATION_HEADER,BEARER + jwtToken)
                .header("Access-Control-Expose-Headers","Authorization")
                .header("role",authenticatedUser.getRole()).build();


    }
}
