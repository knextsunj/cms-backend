package com.github.knextsunj.cms.controller;

import com.github.knextsunj.cms.config.security.TokenIssuer;
import com.github.knextsunj.cms.domain.AccountCredentials;
import com.github.knextsunj.cms.dto.AuthenticatedUser;
import com.github.knextsunj.cms.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;

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
        String jwtToken = null;
        LOGGER.info("login");
        AuthenticatedUser authenticatedUser = null;

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(accountCredentials.getUsername(), accountCredentials.getPassword());
        Subject currentUser = SecurityUtils.getSubject();
        try {
            currentUser.login(usernamePasswordToken);
            authenticatedUser = userService.findUser(accountCredentials.getUsername());
            jwtToken = issuer.issueToken(accountCredentials.getUsername());

        } catch (UnknownAccountException uae) {
            LOGGER.error("Username Not Found!", uae);
            throw uae;
        } catch (IncorrectCredentialsException ice) {
            LOGGER.error("Invalid Credentials!", ice);
            throw ice;
        } catch (LockedAccountException lae) {
            LOGGER.error("Your Account is Locked!", lae);
            throw lae;
        } catch (AuthenticationException ae) {
            LOGGER.error("Unexpected Error!", ae);
            throw ae;
        }

        return Response.ok().header(AUTHORIZATION_HEADER, BEARER + jwtToken)
                .header("Access-Control-Expose-Headers", "Authorization")
                .header("role", authenticatedUser.getRole()).build();
    }
}
