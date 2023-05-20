package com.github.knextsunj.cms.controller;

import com.github.knextsunj.cms.dto.UserPreferenceDTO;
import com.github.knextsunj.cms.service.UserPreferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userpreferences")
public class UserPreferencesController {

    @Autowired
    private UserPreferenceService userPreferenceService;

    @RequestMapping(value = "/fetchUserPreferences/{username}", method = RequestMethod.GET)
    public UserPreferenceDTO fetchUserPreferences(@PathVariable("username") String username) {
        return userPreferenceService.retrieveUserPreferences(username);
    }

}


