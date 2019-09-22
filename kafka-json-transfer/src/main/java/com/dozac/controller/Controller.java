package com.dozac.controller;

import com.dozac.model.CryptoCurrency;
import com.dozac.producer.ProducerKafka;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class Controller {

    @Autowired
    Environment environment;

    @Autowired
    ProducerKafka producer;

    @RequestMapping(value="/json")
    public String getResult(){
        producer.sendMessage(new CryptoCurrency("Bitcoin", 9867d, 12.3d));
        return environment.getProperty("message.response");
    }


}
