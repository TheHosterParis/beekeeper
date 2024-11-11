package com.yvan.beekeeper.application;

import com.yvan.beekeeper.domain.House;
import com.yvan.beekeeper.domain.HouseRepository;
import com.yvan.beekeeper.domain.PersonRepository;
import com.yvan.beekeeper.exposition.kafka.BeeHouseProducer;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@Transactional
public class BeekeeperService {

    private HouseRepository houseRepository;
    private PersonRepository personRepository;
    private BeeHouseProducer beeHouseProducer;

    public BeekeeperService(HouseRepository houseRepository, BeeHouseProducer beeHouseProducer, PersonRepository personRepository) {
        this.houseRepository = houseRepository;
        this.beeHouseProducer = beeHouseProducer;
        this.personRepository = personRepository;
    }

    public void saveHouse(final House house) {
        Long id = new Random().nextLong();
        final House houseToSave = new House(id, house.getName(), house.getDate());
        houseRepository.save(houseToSave);
    }

    public Optional<House> getHouse(final Long id) {
        return houseRepository.findById(id);
    }

    public void deleteHouse(final Long id) {
        houseRepository.deleteById(id);
    }


    public void updateHouse(final Long id, final House houseToUpdate) {

        if (houseRepository.findById(id).isPresent()) {
            final House house = houseRepository.findById(id).get();
            house.setName(houseToUpdate.getName());
            house.setDate(houseToUpdate.getDate());
            houseRepository.save(house);
        } else {
            final House house = new House(new Random().nextLong(),houseToUpdate.getName(), houseToUpdate.getDate());
            houseRepository.save(house);
        }
    }

    //get a random house
    public House getRandomHouse() {
        return houseRepository.findRandomHouse();
    }

    public Iterable<House> getAllHouses() {
        return houseRepository.findAll();
    }

    //for a given house,  get all the bees that are in it since a given date
    public Iterable<House> getAllHousesSince(final String date) {
        return houseRepository.findAllHousesSince(date);
    }

    public void sendHousesToKafka() {
        houseRepository.findAll().forEach(house -> {
            //send to kafka
            beeHouseProducer.sendHouseMessage(house);
        });
    }

}
