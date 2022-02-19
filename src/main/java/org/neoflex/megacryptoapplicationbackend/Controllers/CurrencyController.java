package org.neoflex.megacryptoapplicationbackend.Controllers;


import org.neoflex.megacryptoapplicationbackend.Persistence.Entity.Currency;
import org.neoflex.megacryptoapplicationbackend.Services.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CurrencyController {
    private CurrencyService currencyService;

    @Autowired
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/currencies")
    public List<Currency> getCurrencies() {
        return currencyService.list();
    }
}
