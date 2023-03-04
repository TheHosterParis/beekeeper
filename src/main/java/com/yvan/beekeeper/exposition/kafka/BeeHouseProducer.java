package com.yvan.beekeeper.exposition.kafka;

import com.yvan.beekeeper.domain.House;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class BeeHouseProducer {

    private final KafkaTemplate<String, House> kafkaTemplate;

    //@Value("${spring.kafka.producer.topic}")
    private String topic = "house";

    //configurer le producer avec un json serializer
    //https://www.baeldung.com/spring-kafka-json-serializer-deserializer

    public BeeHouseProducer(KafkaTemplate<String, House> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendHouseMessage(House house) {
        String key = house.getName();
        kafkaTemplate.send(topic, key, house);
    }
}

