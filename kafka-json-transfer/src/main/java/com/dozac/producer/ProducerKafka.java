package com.dozac.producer;

import com.dozac.model.CryptoCurrency;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ProducerKafka {

    @Autowired
    Environment environment;

    @Autowired
    private KafkaTemplate<String, List<CryptoCurrency>> kafkaTemplate;


    public void sendMessage(List<CryptoCurrency> cryptoCurrencies){
        ListenableFuture<SendResult<String, List<CryptoCurrency>>> future = kafkaTemplate
                .send(environment.getProperty("kafka.topic"), "someCrypto", cryptoCurrencies);

        future.addCallback(
                new ListenableFutureCallback<SendResult<String,List<CryptoCurrency>>>() {

                    @Override
                    public void onFailure(Throwable e) {
                        log.error("Something went wrong", e);
                    }

                    @Override
                    public void onSuccess(SendResult<String, List<CryptoCurrency>> result) {
                        log.info("Success");
                    }
                });

    }
}
