//package com.github.knextsunj.cms.config.security;
//
//import com.github.knextsunj.cms.domain.User;
//import com.github.knextsunj.cms.repository.UserRepository;
//
//import javax.annotation.PostConstruct;
//import javax.enterprise.context.RequestScoped;
//import javax.inject.Inject;
//import javax.security.enterprise.identitystore.CredentialValidationResult;
//import javax.security.enterprise.identitystore.IdentityStore;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.Map;
//import java.util.Set;
//
//import static java.util.Arrays.asList;
//import static java.util.Collections.singleton;
//
//import static javax.security.enterprise.identitystore.IdentityStore.ValidationType.PROVIDE_GROUPS;
//
////@RequestScoped
//public class AuthorizationIdentityStore implements IdentityStore {
//    private Map<String, Set<String>> groupsPerCaller;
//
//    @Inject
//    private UserRepository userRepository;
//
////    @PostConstruct
////    public void init() {
////        groupsPerCaller = new HashMap<>();
////        groupsPerCaller.put("payara", new HashSet<>(asList("ROLE_USER", "ROLE_ADMIN")));
////        groupsPerCaller.put("duke", singleton("ROLE_USER"));
////    }
//
//    @Override
//    public Set<String> getCallerGroups(CredentialValidationResult validationResult) {
////        Set<String> result = groupsPerCaller.get(validationResult.getCallerPrincipal().getName());
//        Set<String> result = new HashSet<>();
//
//        User user =  userRepository.findByUsername(validationResult.getCallerPrincipal().getName());
//        String role = user.getRole();
////        if (result == null) {
////            result = emptySet();
////        }
//        if(role!=null) {
//            result.add(role);
//        }
//        return result;
//    }
//
//    @Override
//    public Set<ValidationType> validationTypes() {
//        return singleton(PROVIDE_GROUPS);
//    }
//
//}
