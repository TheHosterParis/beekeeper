package com.yvan.beekeeper.domain;

import java.util.Optional;

public interface HouseRepository {

    Iterable<House> findAllHousesSince(String date);

    Optional<House> findById(Long id);

    void deleteById(Long id);

    Iterable<House> findAll();

    void save(House house);

    House findRandomHouse();
}
