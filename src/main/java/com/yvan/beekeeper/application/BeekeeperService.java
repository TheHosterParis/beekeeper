package com.yvan.beekeeper.application;

import com.yvan.beekeeper.domain.House;
import com.yvan.beekeeper.domain.HouseRepository;
import com.yvan.beekeeper.exposition.kafka.BeeHouseProducer;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@Transactional
public class BeekeeperService {

    @Autowired
    private HouseRepository houseRepository;

    @Autowired
    BeeHouseProducer beeHouseProducer;

    public BeekeeperService() {
    }

    public BeekeeperService(HouseRepository houseRepository) {
        this.houseRepository = houseRepository;
    }

    public void saveHouse(final House house) {
        Long id = new Random().nextLong();
        final House houseToSave = new House(id, house.getName(), house.getDate(), house.getOwner());
        houseRepository.save(houseToSave);
    }

    public Optional<House> getHouse(final Long id) {
        return houseRepository.findById(id);
    }

    public void deleteHouse(final Long id) {
        houseRepository.deleteById(id);
    }


    public void updateHouse(final Long id, final House houseToUpdate) {
        final House house = houseRepository.findById(id).get();
        house.setName(houseToUpdate.getName());
        house.setDate(houseToUpdate.getDate());
        house.setOwner(houseToUpdate.getOwner());
        houseRepository.save(house);
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
