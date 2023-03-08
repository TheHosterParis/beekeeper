package com.yvan.beekeeper.exposition.kafka;

import com.yvan.beekeeper.domain.House;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;

@Component
public class BeeHouseProducer {

    private final KafkaTemplate<String, House> kafkaTemplate;

    //@Value("${spring.kafka.producer.topic}")
    private String topicHouse = "house";

    @Value("${kafka.topic}")
    private String topic;

    @Value("${kafka.message}")
    private String key;

    @Value("${kafka.publish-rate}")
    private int publishRate;

    //configurer le producer avec un json serializer
    //https://www.baeldung.com/spring-kafka-json-serializer-deserializer

    public BeeHouseProducer(KafkaTemplate<String, House> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendHouseMessage(House house) {
        String key = house.getName();
        kafkaTemplate.send(topicHouse, key, house);
    }

    @Scheduled(fixedRateString = "60000")
    public void sendHouseMessage() {
        House house = new House(); // create a new House object
        house.setId(12345L);    // set the id of the house
        house.setName("house1"); // set the name of the house
        house.setOwner("yvy"); // set the owner of the house
        house.setDate(LocalDateTime.now().toString()); // set the date of the house
        kafkaTemplate.send(topicHouse, key, house);
        System.out.println("sendHouseMessage scheaduler published: " + key);
    }
}

