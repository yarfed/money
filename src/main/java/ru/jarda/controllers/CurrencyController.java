package ru.jarda.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.jarda.model.entities.Currency;
import ru.jarda.model.services.CurrencyService;

import java.util.List;

/**
 * Created by User on 14.09.2015.
 */
@Controller
@RequestMapping("/currency/")
public class CurrencyController {
    @Autowired
    CurrencyService currencyService;

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public
    @ResponseBody
    List addCurrency(@RequestBody Currency currency) {

        currencyService.add(currency);
        return currencyService.getAll();
    }

    @RequestMapping(value = "editAll", method = RequestMethod.POST)
    public
    @ResponseBody
    List editAllCurrencies(@RequestBody List<Currency> currencies) {
        currencyService.addAll(currencies);
        return currencyService.getAll();
    }

    @RequestMapping(params = "getAll", method = RequestMethod.GET)
    @ResponseBody
    public List getCurrencies() {
        return currencyService.getAll();
    }

    @RequestMapping(params = "delete", method = RequestMethod.GET)
    @ResponseBody
    public List deleteCurrency(long id) {
        currencyService.delete(id);
        return currencyService.getAll();
    }
}
