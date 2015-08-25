package ru.jarda.controllers;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.jarda.model.entities.Account;
import ru.jarda.model.entities.InCategory;
import ru.jarda.model.entities.Operation;
import ru.jarda.model.entities.OutCategory;
import ru.jarda.model.services.AccountService;
import ru.jarda.model.services.CategoryService;
import ru.jarda.model.services.OperationService;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/")
public class MainController  {
    @Autowired
    AccountService accountService;
    @Autowired
    CategoryService inCategoryService;
    @Autowired
    CategoryService outCategoryService;
    @Autowired
    OperationService operationService;

    @RequestMapping( method = RequestMethod.GET)
    public String home() {
        return "mainAngular";
    }

    @RequestMapping(value = "add_account", method = RequestMethod.POST)
    public  @ResponseBody List addAccount(@RequestBody Account account) {
            accountService.add(account);
            return accountService.getAll();
    }

    @RequestMapping(params = "getAccounts", method = RequestMethod.GET)
    @ResponseBody
    public List getAccountService(){
        return accountService.getAll();
    }

    @RequestMapping(params = "deleteAccount", method = RequestMethod.GET)
    @ResponseBody
    public List deleteAccount (long id){
        accountService.delete(id);
        return accountService.getAll();
    }

    @RequestMapping(params = "get_income_categories", method = RequestMethod.GET)
    @ResponseBody
    public List getIncomingCategories (){
      return  inCategoryService.getAll();
    }

    @RequestMapping(value = "add_income_category", method = RequestMethod.POST)
    public  @ResponseBody List addIncomeCategory(@RequestBody InCategory category) {
        if (category.getId()==null) {
            inCategoryService.add(category);
        } else {
            inCategoryService.edit(category);
        }
        return inCategoryService.getAll();
    }

    @RequestMapping(value = "add_expense_category", method = RequestMethod.POST)
    public  @ResponseBody List addExpenseCategory(@RequestBody OutCategory category) {
        if (category.getId()==null) {
            outCategoryService.add(category);
        } else {
            outCategoryService.edit(category);
        }
        return outCategoryService.getAll();
    }

    @RequestMapping(params = "del_income_category", method = RequestMethod.GET)
    @ResponseBody
    public List deleteIncomingCategory (long id){
        inCategoryService.delete(id);
        return  inCategoryService.getAll();
    }

    @RequestMapping(params = "get_expense_categories", method = RequestMethod.GET)
    @ResponseBody
    public List getOutgoingCategories (){
        return  outCategoryService.getAll();
    }

    @RequestMapping(params = "del_expense_category", method = RequestMethod.GET)
    @ResponseBody
    public List deleteOutgoingCategory (long id){
        outCategoryService.delete(id);
        return  outCategoryService.getAll();
    }



    @RequestMapping(params = "getOperations", method = RequestMethod.GET)
    @ResponseBody
    public List getOperations(){
        return operationService.getAll();
    }

    @RequestMapping(value = "addOperation", method = RequestMethod.POST)
    public  @ResponseBody List addOperation(@RequestBody Operation operation) {
        operationService.add(operation);
        return operationService.getAll();
    }



    @RequestMapping(params = "deleteOperation", method = RequestMethod.GET)
    @ResponseBody
    public List deleteOperation (long id){
        operationService.delete(id);
        return  operationService.getAll();
    }

    @ExceptionHandler({ConstraintViolationException.class,DataIntegrityViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
   String handleBadRequests(Exception ex) throws IOException {
        System.out.println(ex);
        return  "{\"errorMessage\":\""+ex.getMessage()+"\"}";
   }
}




