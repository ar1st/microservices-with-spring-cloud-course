package com.aris.currencyexchangeservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {

    public static final String PORT = "local.server.port";
    @Autowired
    private Environment environment;

    @GetMapping("current-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {

        CurrencyExchange currencyExchange = new CurrencyExchange();

        currencyExchange.setId(100L);
        currencyExchange.setFrom(from);
        currencyExchange.setTo(to);
        currencyExchange.setConversionMultiple(BigDecimal.valueOf(50));
        currencyExchange.setEnvironment(environment.getProperty(PORT));

        return currencyExchange;
    }
}
