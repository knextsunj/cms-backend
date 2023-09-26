package com.github.knextsunj.cms.controller;

import com.github.knextsunj.cms.domain.AccountCredentials;
import com.github.knextsunj.cms.dto.AuthenticatedUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.security.enterprise.SecurityContext;
import javax.ws.rs.core.Response;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;


@Stateless
@Path("/")
public class LoginController {

    private static final Logger LOGGER = LogManager.getLogger(LoginController.class.getName());

    @Inject
    private SecurityContext securityContext;

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response doLogin() {
//        var role ="";
//           UsernamePasswordAuthenticationToken creds =
//                new UsernamePasswordAuthenticationToken(
//                        credentials.getUsername(),
//                        credentials.getPassword());
//
//        Authentication auth = authenticationManager.authenticate(creds);
//           Optional<String> optionalRole = auth.getAuthorities().stream().map((grantedAuthority -> {
//                return grantedAuthority.getAuthority();
//            })).findFirst();
//
//           if(optionalRole.isPresent()) {
//                role = optionalRole.get();
//           }
//        // Generate token
//        String jwts = jwtService.getToken(auth.getName());
//
//        // Build response with the generated token
//        return ResponseEntity.ok()
//                .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwts)
//                .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Authorization")
//                .header("role",role)
//                .build();

        LOGGER.info("login");
        if (securityContext.getCallerPrincipal() != null) {
            AuthenticatedUser authenticatedUser = new AuthenticatedUser(securityContext.getCallerPrincipal().getName());
            return Response.ok(authenticatedUser).build();
        }
        return Response.status(UNAUTHORIZED).build();

    }
}
