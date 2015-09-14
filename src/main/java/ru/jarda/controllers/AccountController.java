package ru.jarda.controllers;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.jarda.model.entities.Account;
import ru.jarda.model.services.AccountService;

import java.io.IOException;
import java.util.List;

/**
 * Created by User on 14.09.2015.
 */
@Controller
@RequestMapping("/account/")
public class AccountController {
    @Autowired
    AccountService accountService;

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public
    @ResponseBody
    List addAccount(@RequestBody Account account) {
        accountService.add(account);
        return accountService.getAll();
    }

    @RequestMapping(params = "getAll", method = RequestMethod.GET)
    @ResponseBody
    public List getAccounts() {
        return accountService.getAll();
    }

    @RequestMapping(params = "delete", method = RequestMethod.GET)
    @ResponseBody
    public List deleteAccount(long id) {
        accountService.delete(id);
        return accountService.getAll();
    }

    @ExceptionHandler({ConstraintViolationException.class, DataIntegrityViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    String handleBadRequests(Exception ex) throws IOException {
        System.out.println(ex);
        return "{\"errorMessage\":\"" + ex.getMessage() + "\"}";
    }
}
