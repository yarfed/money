package ru.jarda.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.jarda.model.entities.Property;
import ru.jarda.model.services.PropertyService;

/**
 * Created by User on 14.09.2015.
 */
@Controller
@RequestMapping("/property/")
public class PropertyController {

    @Autowired
    PropertyService propertyService;

    @RequestMapping(params = "get", method = RequestMethod.GET)
    @ResponseBody
    public Property getProperty(String name) {
        return propertyService.getByName(name);
    }

    @RequestMapping(params = "set", method = RequestMethod.GET)
    @ResponseBody
    public Property setProperty(String name, String value) {
        propertyService.setProperty(name, value);
        return propertyService.getByName(name);
    }


}
