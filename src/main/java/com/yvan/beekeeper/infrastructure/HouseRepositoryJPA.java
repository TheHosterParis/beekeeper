package com.yvan.beekeeper.infrastructure;

import com.yvan.beekeeper.domain.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface HouseRepositoryJPA extends JpaRepository<House, Long> {

    //for a given house,  get all the bees that are in it since a given date
    @Query(value = "SELECT * FROM house WHERE date > ?1", nativeQuery = true)
    Iterable<House> findAllHousesSince(String date);

    @Query(value = "SELECT * FROM House ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    House findRandomHouse();
}