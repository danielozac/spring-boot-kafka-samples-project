package com.dozac.controller;

import com.dozac.model.CryptoCurrency;
import com.dozac.producer.ProducerKafka;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/kafka")
public class Controller {

    @Autowired
    Environment environment;

    @Autowired
    ProducerKafka producer;

    @RequestMapping(value="/json")
    public String getResult(){
        List<CryptoCurrency> cryptoCurrencyList = Stream
                .of(new CryptoCurrency("Bitcoin", 9867d, 12.3d),
                    new CryptoCurrency("Ripple", 255d, 11.91d),
                    new CryptoCurrency("Litecoin", 72.52d, 4.53d))
                .collect(Collectors.toList());

        producer.sendMessage(cryptoCurrencyList);
        return environment.getProperty("message.response");
    }


}
