package ru.jarda.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.jarda.model.services.CustomUserDetailsService;

/**
 * Created by User on 27.05.2015.
 */
@Controller

public class LoginController {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @RequestMapping(value = "login/form", method = RequestMethod.GET)
    public String login(ModelMap model) {
        return "login";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void registration(String username, String password) {
        customUserDetailsService.add(username, password);
    }
}
