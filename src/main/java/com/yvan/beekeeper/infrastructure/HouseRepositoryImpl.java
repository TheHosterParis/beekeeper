package com.yvan.beekeeper.infrastructure;

import com.yvan.beekeeper.domain.House;
import com.yvan.beekeeper.domain.HouseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class HouseRepositoryImpl implements HouseRepository {

    private final HouseRepositoryJPA houseRepository;

    public HouseRepositoryImpl(HouseRepositoryJPA houseRepository) {
        this.houseRepository = houseRepository;
    }

    @Override
    public Iterable<House> findAllHousesSince(String date) {
        return houseRepository.findAllHousesSince(date);
    }

    @Override
    public Optional<House> findById(Long id) {
        return houseRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        houseRepository.deleteById(id);
    }

    @Override
    public Iterable<House> findAll() {
        return houseRepository.findAll();
    }

    @Override
    public void save(House house) {
        houseRepository.save(house);
    }

    @Override
    public House findRandomHouse() {
        return houseRepository.findRandomHouse();
    }
}
