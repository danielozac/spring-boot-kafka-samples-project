package com.dozac.producer;

import com.dozac.model.CryptoCurrency;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
public class ProducerKafka {

    @Autowired
    Environment environment;

    @Autowired
    private KafkaTemplate<String, CryptoCurrency> kafkaTemplate;


    public void sendMessage(CryptoCurrency cryptoCurrency){
        ListenableFuture<SendResult<String, CryptoCurrency>> future = kafkaTemplate
                .send(environment.getProperty("kafka.topic"), "someCrypto", cryptoCurrency);

        future.addCallback(
                new ListenableFutureCallback<SendResult<String,CryptoCurrency>>() {

                    @Override
                    public void onFailure(Throwable ex) {
                        log.error("Inside Exception", ex);
                    }

                    @Override
                    public void onSuccess(SendResult<String, CryptoCurrency> result) {
                        log.info("Success");
                    }
                });

    }
}
