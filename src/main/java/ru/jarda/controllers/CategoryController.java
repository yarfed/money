package ru.jarda.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.jarda.model.entities.InCategory;
import ru.jarda.model.entities.OutCategory;
import ru.jarda.model.services.CategoryService;

import java.util.List;

/**
 * Created by User on 14.09.2015.
 */
@Controller
@RequestMapping("/category/")
public class CategoryController {
    @Autowired
    CategoryService inCategoryService;
    @Autowired
    CategoryService outCategoryService;

    @RequestMapping(value = "income", params = "get", method = RequestMethod.GET)
    @ResponseBody
    public List getIncomingCategories() {
        return inCategoryService.getAll();
    }

    @RequestMapping(value = "income/add", method = RequestMethod.POST)
    public
    @ResponseBody
    List addIncomeCategory(@RequestBody InCategory category) {
        if (category.getId() == null) {
            inCategoryService.add(category);
        } else {
            inCategoryService.edit(category);
        }
        return inCategoryService.getAll();
    }

    @RequestMapping(value = "expense/add", method = RequestMethod.POST)
    public
    @ResponseBody
    List addExpenseCategory(@RequestBody OutCategory category) {
        if (category.getId() == null) {
            outCategoryService.add(category);
        } else {
            outCategoryService.edit(category);
        }
        return outCategoryService.getAll();
    }

    @RequestMapping(value = "income", params = "delete", method = RequestMethod.GET)
    @ResponseBody
    public List deleteIncomingCategory(long id) {
        inCategoryService.delete(id);
        return inCategoryService.getAll();
    }

    @RequestMapping(value = "expense", params = "get", method = RequestMethod.GET)
    @ResponseBody
    public List getOutgoingCategories() {
        return outCategoryService.getAll();
    }

    @RequestMapping(value = "expense", params = "delete", method = RequestMethod.GET)
    @ResponseBody
    public List deleteOutgoingCategory(long id) {
        outCategoryService.delete(id);
        return outCategoryService.getAll();
    }


}
