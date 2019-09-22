package com.dozac.config;

import com.dozac.consumer.ConsumerKafka;
import com.dozac.model.CryptoCurrency;
import com.dozac.producer.ProducerKafka;
import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
public class KafkaConfig {

    @Autowired
    Environment environment;

    /**
     * Consumer Configuration
     */
    @Bean
    public Map<String, Object> consumerConfig() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, environment.getProperty("kafka.broker"));
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, environment.getProperty("enable.auto.commit"));
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, environment.getProperty("auto.commit.interval.ms"));
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, environment.getProperty("group.id"));
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, environment.getProperty("kafka.auto.offset.reset"));
        return props;
    }

    @Bean
    public ConsumerFactory<String, CryptoCurrency> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfig(), new StringDeserializer(),
                new JsonDeserializer<>(CryptoCurrency.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, CryptoCurrency> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, CryptoCurrency> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    @Bean
    public ConsumerKafka consumerKafka() {
        return new ConsumerKafka();
    }


    /**
     * Producer Configuration
     */
    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, environment.getProperty("kafka.broker"));
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        props.put(ProducerConfig.LINGER_MS_CONFIG, 1);

        return props;
    }

    @Bean
    public ProducerFactory<String, CryptoCurrency> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<String, CryptoCurrency> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public ProducerKafka producerKafka() {
        return new ProducerKafka();
    }
}
