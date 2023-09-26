package com.github.knextsunj.cms.config.security;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.github.knextsunj.cms.domain.User;
import com.github.knextsunj.cms.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import static java.util.Collections.singleton;
import static javax.security.enterprise.identitystore.CredentialValidationResult.INVALID_RESULT;
import static javax.security.enterprise.identitystore.CredentialValidationResult.NOT_VALIDATED_RESULT;
import static javax.security.enterprise.identitystore.IdentityStore.ValidationType.VALIDATE;

@RequestScoped
public class AuthenticationIdentityStore implements IdentityStore {

    private Map<String, String> callerToPassword;

    @Inject
    private UserRepository userRepository;

//    @PostConstruct
//    public void init() {
//        callerToPassword = new HashMap<>();
//        callerToPassword.put("payara", "fish");
//        callerToPassword.put("duke", "secret");
//    }

    @Override
    public CredentialValidationResult validate(Credential credential) {
        CredentialValidationResult result = null;

        if (credential instanceof UsernamePasswordCredential) {
            UsernamePasswordCredential usernamePassword = (UsernamePasswordCredential) credential;
//            String expectedPW = callerToPassword.get(usernamePassword.getCaller());
            User user = userRepository.findByUsername(usernamePassword.getCaller());
//            if (expectedPW != null && expectedPW.equals(usernamePassword.getPasswordAsString())) {
//                result = new CredentialValidationResult(usernamePassword.getCaller());
//            }
           if(Objects.nonNull(user)) {
               BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
               String encodedPwd = bCryptPasswordEncoder.encode(String.valueOf(usernamePassword.getPassword().getValue()));
//               String bcryptHashString = BCrypt.withDefaults().hashToString(12, usernamePassword.getPassword().getValue());
               if(encodedPwd.equals(user.getPassword())) {
                   result = new CredentialValidationResult(usernamePassword.getCaller());
               }
           }

            else {
                result = INVALID_RESULT;
            }
        } else {
            result = NOT_VALIDATED_RESULT;
        }
        return result;
    }

    @Override
    public Set<ValidationType> validationTypes() {
        return singleton(VALIDATE);
    }


}
