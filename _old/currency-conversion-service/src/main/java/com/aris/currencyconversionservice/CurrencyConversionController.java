package com.aris.currencyconversionservice;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class CurrencyConversionController {

    private final CurrencyExchangeProxy currencyExchangeProxy;

    @GetMapping("currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversion(@PathVariable String from, @PathVariable String to,
                                                          @PathVariable BigDecimal quantity) {

        final ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate().getForEntity("http://localhost:8000/current-exchange/from/{from}/to/{to}",
                                                                                             CurrencyConversion.class, Map.of("from", from, "to", to));


        final CurrencyConversion currencyConversion = responseEntity.getBody();

        return new CurrencyConversion(currencyConversion.getId(), from, to, quantity, currencyConversion.getConversionMultiple(),
                                      quantity.multiply(currencyConversion.getConversionMultiple()),
                                      currencyConversion.getEnvironment());
    }

    @GetMapping("currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversionFeign(@PathVariable String from, @PathVariable String to,
                                                          @PathVariable BigDecimal quantity) {

        final CurrencyConversion currencyConversion = currencyExchangeProxy.retrieveExchangeValue(from, to);

        return new CurrencyConversion(currencyConversion.getId(), from, to, quantity, currencyConversion.getConversionMultiple(),
                                      quantity.multiply(currencyConversion.getConversionMultiple()),
                                      currencyConversion.getEnvironment());
    }


}
