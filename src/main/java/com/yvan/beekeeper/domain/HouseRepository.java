package com.yvan.beekeeper.domain;

import java.util.Optional;

public interface HouseRepository {

    public Iterable<House> findAllHousesSince(String date);

    public Optional<House> findById(Long id);

    public void deleteById(Long id);

    public Iterable<House> findAll();

    public void save(House house);

    House findRandomHouse();
}
