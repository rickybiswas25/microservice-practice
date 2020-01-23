package com.example.forexservice.controller;

import com.example.forexservice.entity.ExchangeValue;
import com.example.forexservice.repository.ExchangeValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ForexController {
    private final Environment env;
    private final ExchangeValueRepository exchangeValueRepo;

    @Autowired
    public ForexController(Environment env, ExchangeValueRepository exchangeValueRepo) {
        this.env = env;
        this.exchangeValueRepo = exchangeValueRepo;
    }

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public ExchangeValue findByFromAndTo(@PathVariable("from") String from, @PathVariable("to") String to) {
        ExchangeValue exchangeValue = this.exchangeValueRepo.findByFromAndTo(from, to);
        exchangeValue.setPort(Integer.parseInt(env.getProperty("local.server.port")));
        return exchangeValue;
    }
}
