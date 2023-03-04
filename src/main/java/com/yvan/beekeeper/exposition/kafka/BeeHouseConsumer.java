package com.yvan.beekeeper.exposition.kafka;

import com.yvan.beekeeper.application.BeekeeperService;
import com.yvan.beekeeper.domain.House;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class BeeHouseConsumer {
    //create a consumer that will listen to the topic house. the consumer log the message received in the console, the comsumer insert the message in the database

    @Autowired
    private BeekeeperService BeekeeperService;

    @KafkaListener(topics = "house", groupId = "house-group")
    public void consume(House message) {
        // Log the received message
        System.out.println("Message logged: " + message);
        message.setDate(Date.from(java.time.Instant.now()).toString());
        // Pass the message to updateHouses method
        BeekeeperService.updateHouse(message.getId(), message);
    }
}