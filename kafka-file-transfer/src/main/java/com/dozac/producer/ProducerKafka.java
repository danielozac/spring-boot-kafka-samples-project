package com.dozac.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class ProducerKafka {
	
	 @Autowired
	 private KafkaTemplate<String, Object> kafkaTemplate;

	 @Autowired
     Environment environment;

	public void sendMessage(String message, byte[] bytes){
		Map<String, byte[]> map = new HashMap();
		map.put(message, bytes);

		ProducerRecord producerRecord = new ProducerRecord<String, Map>(environment.getProperty("kafka.topic"), message, map);

		ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(producerRecord);
		future.addCallback(
				new ListenableFutureCallback<SendResult<String,Object>>() {

					@Override
					public void onFailure(Throwable e) {
						log.error("Something went wrong", e);
					}

					@Override
					public void onSuccess(SendResult<String, Object> result) {
                        log.info("Success.");
					}
				});
	}

}
