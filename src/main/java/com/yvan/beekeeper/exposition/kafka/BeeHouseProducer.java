package com.yvan.beekeeper.exposition.kafka;

import com.yvan.beekeeper.application.BeekeeperService;
import com.yvan.beekeeper.domain.House;
import com.yvan.beekeeper.domain.HouseRepository;
import com.yvan.beekeeper.domain.Person;
import com.yvan.beekeeper.domain.PersonRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.random.RandomGenerator;

@Component
public class BeeHouseProducer {

    private final KafkaTemplate<String, House> kafkaTemplate;
    private final HouseRepository houseRepository;
    private final PersonRepository personRepository;
    //@Value("${spring.kafka.producer.topic}")
    private final String topicHouse = "house";

    @Value("${kafka.topic}")
    private String topic;

    @Value("${kafka.message}")
    private String key;

    @Value("${kafka.publish-rate}")
    private int publishRate;

    //configurer le producer avec un json serializer
    //https://www.baeldung.com/spring-kafka-json-serializer-deserializer

    public BeeHouseProducer(KafkaTemplate<String, House> kafkaTemplate, HouseRepository houseRepository, PersonRepository personRepository) {
        this.kafkaTemplate = kafkaTemplate;
        this.houseRepository = houseRepository;
        this.personRepository = personRepository;
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
        House randomHouse = houseRepository.findRandomHouse();
        randomHouse.setDate(LocalDateTime.now().toString());
        return randomHouse;
    }

    private House CreateHouse() {
        House house = new House(); // create a new House object
        house.setId(new Random().nextLong());    // set an id of the house
        // create a random name for the house to set with name of French city
        String[] names = {"Marseille", "Paris", "Lyon", "Toulouse", "Nice", "Nantes", "Strasbourg", "Montpellier", "Bordeaux", "Lille", "Rennes", "Reims", "Le Havre", "Saint-Étienne", "Toulon", "Grenoble", "Dijon", "Angers", "Nîmes", "Villeurbanne", "Le Mans", "Aix-en-Provence", "Brest", "Limoges", "Tours", "Amiens", "Metz", "Perpignan", "Boulogne-Billancourt", "Besançon", "Orléans", "Rouen", "Mulhouse", "Caen", "Nancy", "Saint-Denis", "Argenteuil", "Montreuil", "Nanterre", "Roubaix", "Tourcoing", "Avignon", "Créteil", "Dunkerque", "Aulnay-sous-Bois", "Poitiers", "Versailles", "Asnières-sur-Seine", "Colombes", "Narbonne", "Vitry-sur-Seine", "Ajaccio", "Aubervilliers", "Saint-Denis", "Saint-Paul", "Clermont-Ferrand", "Cannes", "Ant"};
        house.setName(names[new Random().nextInt(names.length)]); // set the name of the house
        house.setAddress("address " + new Random().nextInt());;
        //String coordinates = new Random().nextDouble() + "," + new Random().nextDouble();
        String coordinates = generateRandomCoordinates();
        house.setCoordinates(coordinates); // set the coordinates of the house
        // create a random owner for the house to set
        house.setDate(LocalDateTime.now().toString()); // set the date of the house
        Person randomPerson = personRepository.findRandomPerson();
        house.setPerson(randomPerson);
        return house;
    }

    public static String generateRandomCoordinates() {
        Random random = new Random();

        // Générer une latitude aléatoire entre -90 et 90 degrés
        double latitude = -90 + (180 * random.nextDouble());

        // Générer une longitude aléatoire entre -180 et 180 degrés
        double longitude = -180 + (360 * random.nextDouble());

        // Formater les coordonnées avec 12 décimales
        return String.format("%.12f, %.12f", latitude, longitude);
    }

    private static Person CreatePerson() {
        Person person = new Person(); // create a new Person object
        person.setId(new Random().nextLong());    // set an id of the person
        // create a random name for the person to set with name of French city
        String[] names = {"Marseille", "Paris", "Lyon", "Toulouse", "Nice", "Nantes", "Strasbourg", "Montpellier", "Bordeaux", "Lille", "Rennes", "Reims", "Le Havre", "Saint-Étienne", "Toulon", "Grenoble", "Dijon", "Angers", "Nîmes", "Villeurbanne", "Le Mans", "Aix-en-Provence", "Brest", "Limoges", "Tours", "Amiens", "Metz", "Perpignan", "Boulogne-Billancourt", "Besançon", "Orléans", "Rouen", "Mulhouse", "Caen", "Nancy", "Saint-Denis", "Argenteuil", "Montreuil", "Nanterre", "Roubaix", "Tourcoing", "Avignon", "Créteil", "Dunkerque", "Aulnay-sous-Bois", "Poitiers", "Versailles", "Asnières-sur-Seine", "Colombes", "Narbonne", "Vitry-sur-Seine", "Ajaccio", "Aubervilliers", "Saint-Denis", "Saint-Paul", "Clermont-Ferrand", "Cannes", "Ant"};
        person.setName(names[new Random().nextInt(names.length)]); // set the name of the person
        // create a random owner for the person to set
        String[] owners = {"yvan", "yves", "yvon", "yvonne", "yvann", "yvannick", "yvanick", "yvanne", "yvannic", "yvannick"};
        person.setDate(LocalDateTime.now().toString()); // set the date of the person
        return person;
    }
}

