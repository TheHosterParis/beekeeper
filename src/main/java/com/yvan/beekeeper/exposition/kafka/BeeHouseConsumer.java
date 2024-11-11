package com.yvan.beekeeper.exposition.kafka;

import com.yvan.beekeeper.application.BeekeeperService;
import com.yvan.beekeeper.domain.House;
import com.yvan.beekeeper.domain.HouseRepository;
import com.yvan.beekeeper.domain.Person;
import com.yvan.beekeeper.domain.PersonRepository;
import org.springframework.stereotype.Component;
import org.springframework.kafka.annotation.KafkaListener;

@Component
public class BeeHouseConsumer {
    //create a consumer that will listen to the topic house. the consumer log the message received in the console, the comsumer insert the message in the database


    private final BeekeeperService beekeeperService;
    private final HouseRepository houseRepository;
    private final PersonRepository personRepository;

    public BeeHouseConsumer(BeekeeperService beekeeperService, HouseRepository houseRepository, PersonRepository personRepository) {
        this.beekeeperService = beekeeperService;
        this.houseRepository = houseRepository;
        this.personRepository = personRepository;
    }

    @KafkaListener(topics = "house", groupId = "house-group")
    public void consume(House message) {
        // Log the received message
        System.out.println("Message consuming: " + message);
        // Pass the message to updateHouses method
        Person randomPerson = personRepository.findRandomPerson();
        message.setPerson(randomPerson);
        houseRepository.save(message);
    }
}