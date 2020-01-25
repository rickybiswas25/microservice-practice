package com.example.currencyexchange.controller;

import com.example.currencyexchange.client.proxy.CurrencyConversionProxy;
import com.example.currencyexchange.model.CurrencyConversionBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyConversionController {

    private final CurrencyConversionProxy proxy;

    @Autowired
    public CurrencyConversionController(CurrencyConversionProxy proxy) {
        this.proxy = proxy;
    }

    @GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrency(@PathVariable String from, @PathVariable String to,
            @PathVariable BigDecimal quantity) {

        CurrencyConversionBean response = this.proxy.findExchangeValue(from, to);
        return new CurrencyConversionBean(response.getId(), from, to, response.getConversionMultiple(), quantity,
                quantity.multiply(response.getConversionMultiple()), response.getPort());
    }

}
