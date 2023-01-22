package com.yvan.beekeeper.application;

import com.yvan.beekeeper.domain.House;
import com.yvan.beekeeper.infrastructure.HouseRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class BeekeeperService {

    @Autowired
    HouseRepository houseRepository;

    @Transactional
    public void saveHouse(final String name) {
        Long id = new Random().nextLong();
        final House house = new House(id, name);
        houseRepository.save(house);
    }

    @Transactional
    public Optional<House> getHouse(final Long id) {
        return houseRepository.findById(id);
    }
}
