//package com.github.knextsunj.cms.config.security;
//
//import com.github.knextsunj.cms.config.security.model.JWTCredential;
//import io.jsonwebtoken.ExpiredJwtException;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.security.Keys;
//import org.apache.logging.log4j.Level;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//import javax.inject.Inject;
//import javax.security.enterprise.AuthenticationException;
//import javax.security.enterprise.AuthenticationStatus;
//import javax.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
//import javax.security.enterprise.authentication.mechanism.http.HttpMessageContext;
//import javax.security.enterprise.credential.UsernamePasswordCredential;
//import javax.security.enterprise.identitystore.CredentialValidationResult;
//import javax.security.enterprise.identitystore.IdentityStoreHandler;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import java.security.Key;
//
//import static com.github.knextsunj.cms.config.security.Constants.AUTHORIZATION_HEADER;
//import static com.github.knextsunj.cms.config.security.Constants.BEARER;
//
//public class JwtAuthenticationMechanism implements HttpAuthenticationMechanism {
//
//    private static final Logger LOGGER = LogManager.getLogger(JwtAuthenticationMechanism.class);
//
//    @Inject
//    private IdentityStoreHandler identityStoreHandler;
//
//    @Inject
//    private TokenProvider tokenProvider;
//
//    static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
//
//    static final String PREFIX = "Bearer";
//
//    @Override
//    public AuthenticationStatus validateRequest(HttpServletRequest request, HttpServletResponse response, HttpMessageContext context) throws AuthenticationException {
//       // return null;
//        LOGGER.log(Level.INFO, "validateRequest: {0}", request.getRequestURI());
//        // Get the (caller) name and password from the request
//        // NOTE: This is for the smallest possible example only. In practice
//        // putting the password in a request query parameter is highly insecure
//
//
//        if(request.getMethod().equals("OPTIONS")) {
//                return context.doNothing();
//           }
//
////        UsernamePasswordCredential usernamePasswordCredential =  (UsernamePasswordCredential) context.getAuthParameters().getCredential();
//
//        String name = request.getParameter("username");
//        String password = request.getParameter("password");
//        String token = extractToken(context);
//
//
//        if (name != null && password != null) {
//            LOGGER.log(Level.INFO, "credentials : {0}, {1}", new String[]{name, password});
//            // validation of the credential using the identity store
//            CredentialValidationResult result = identityStoreHandler.validate(new UsernamePasswordCredential(name,password));
//            if (result.getStatus() == CredentialValidationResult.Status.VALID) {
//                // Communicate the details of the authenticated user to the container and return SUCCESS.
//                return createToken(result, context);
//            }
//            // if the authentication failed, we return the unauthorized status in the http response
//            return context.responseUnauthorized();
//        } else if (token != null) {
//            // validation of the jwt credential
//            return validateToken(token, context);
//        } else if (context.isProtected()) {
//            // A protected resource is a resource for which a constraint has been defined.
//            // if there are no credentials and the resource is protected, we response with unauthorized status
//            return context.responseUnauthorized();
//        }
//        // there are no credentials AND the resource is not protected,
//        // SO Instructs the container to "do nothing"
//        return context.doNothing();
//    }
//
//    @Override
//    public AuthenticationStatus secureResponse(HttpServletRequest request, HttpServletResponse response, HttpMessageContext httpMessageContext) throws AuthenticationException {
//        return HttpAuthenticationMechanism.super.secureResponse(request, response, httpMessageContext);
//    }
//
//    @Override
//    public void cleanSubject(HttpServletRequest request, HttpServletResponse response, HttpMessageContext httpMessageContext) {
//        HttpAuthenticationMechanism.super.cleanSubject(request, response, httpMessageContext);
//    }
//
//
//    private AuthenticationStatus validateToken(String token, HttpMessageContext context) {
//        try {
//            if (tokenProvider.validateToken(token)) {
//                JWTCredential credential = tokenProvider.getCredential(token);
//                return context.notifyContainerAboutLogin(credential.getPrincipal(), credential.getAuthorities());
//            }
//             // if token invalid, response with unauthorized status
//            return context.responseUnauthorized();
//        } catch (ExpiredJwtException eje) {
//            LOGGER.log(Level.INFO, "Security exception for user {0} - {1}", new String[]{eje.getClaims().getSubject(), eje.getMessage()});
//            return context.responseUnauthorized();
//        }
//    }
//
//    private AuthenticationStatus createToken(CredentialValidationResult result, HttpMessageContext context) {
//            String jwt = tokenProvider.createToken(result.getCallerPrincipal().getName(), result.getCallerGroups(), false,key);
//            context.getResponse().setHeader(AUTHORIZATION_HEADER, BEARER + jwt);
//            context.getResponse().setHeader("Access-Control-Expose-Headers","Authorization");
//        return context.notifyContainerAboutLogin(result.getCallerPrincipal(), result.getCallerGroups());
//    }
//
//    private String extractToken(HttpMessageContext context) {
//        String token = context.getRequest().getHeader(AUTHORIZATION_HEADER);
//        if (token != null) {
////            String token = authorizationHeader.substring(BEARER.length(), authorizationHeader.length());
////            return token;
//
//            String user = Jwts.parserBuilder()
//                    .setSigningKey(key)
//                    .build()
//                    .parseClaimsJws(token.replace(PREFIX, ""))
//                    .getBody()
//                    .getSubject();
//            return user;
//        }
//
//        return null;
//    }
//}
