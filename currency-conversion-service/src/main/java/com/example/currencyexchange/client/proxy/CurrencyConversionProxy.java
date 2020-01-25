package com.example.currencyexchange.client.proxy;

import com.example.currencyexchange.model.CurrencyConversionBean;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "forex-service")
@RibbonClient(name = "forex-service")
public interface CurrencyConversionProxy {

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    CurrencyConversionBean findExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to);
}
