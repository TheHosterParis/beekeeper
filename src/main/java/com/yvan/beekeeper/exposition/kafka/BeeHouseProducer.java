package com.yvan.beekeeper.exposition.kafka;

import com.yvan.beekeeper.application.BeekeeperService;
import com.yvan.beekeeper.domain.House;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private BeekeeperService beekeeperService;

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
        House house = CreateHouse();
        kafkaTemplate.send(topicHouse, key, house);
        System.out.println("sendHouseMessage scheaduler published: " + key);
    }

    @Scheduled(fixedRateString = "6000")
    public void sendUpdateRandomHouse() {
        House house = UpdateRandomHouse();
        kafkaTemplate.send(topicHouse, key, house);
        System.out.println("sendUpdateHouseMessage scheaduler published: " + key);
    }

    private House UpdateRandomHouse() {
        House house = beekeeperService.getRandomHouse();
        return house;
    }

    private static House CreateHouse() {
        House house = new House(); // create a new House object
        house.setId(new Random().nextLong());    // set an id of the house
        // create a random name for the house to set with name of French city
        String[] names = {"Marseille", "Paris", "Lyon", "Toulouse", "Nice", "Nantes", "Strasbourg", "Montpellier", "Bordeaux", "Lille", "Rennes", "Reims", "Le Havre", "Saint-Étienne", "Toulon", "Grenoble", "Dijon", "Angers", "Nîmes", "Villeurbanne", "Le Mans", "Aix-en-Provence", "Brest", "Limoges", "Tours", "Amiens", "Metz", "Perpignan", "Boulogne-Billancourt", "Besançon", "Orléans", "Rouen", "Mulhouse", "Caen", "Nancy", "Saint-Denis", "Argenteuil", "Montreuil", "Nanterre", "Roubaix", "Tourcoing", "Avignon", "Créteil", "Dunkerque", "Aulnay-sous-Bois", "Poitiers", "Versailles", "Asnières-sur-Seine", "Colombes", "Narbonne", "Vitry-sur-Seine", "Ajaccio", "Aubervilliers", "Saint-Denis", "Saint-Paul", "Clermont-Ferrand", "Cannes", "Ant"};
        house.setName(names[new Random().nextInt(names.length)]); // set the name of the house
        // create a random owner for the house to set
        String[] owners = {"yvan", "yves", "yvon", "yvonne", "yvann", "yvannick", "yvanick", "yvanne", "yvannic", "yvannick"};
        house.setOwner(owners[new Random().nextInt(owners.length)]); // set the owner of the house
        house.setDate(LocalDateTime.now().toString()); // set the date of the house
        return house;
    }
}

