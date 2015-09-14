package ru.jarda.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.jarda.model.entities.Operation;
import ru.jarda.model.services.OperationService;

import java.util.List;

/**
 * Created by User on 14.09.2015.
 */
@Controller
@RequestMapping("/operation/")
public class OperationController {
    @Autowired
    OperationService operationService;

    @RequestMapping(params = "getAll", method = RequestMethod.GET)
    @ResponseBody
    public List getOperations() {
        return operationService.getAll();
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public
    @ResponseBody
    List addOperation(@RequestBody Operation operation) {
        operationService.add(operation);
        return operationService.getAll();
    }


    @RequestMapping(params = "delete", method = RequestMethod.GET)
    @ResponseBody
    public List deleteOperation(long id) {
        operationService.delete(id);
        return operationService.getAll();
    }
}
