package com.yvan.beekeeper.infrastructure;

import com.yvan.beekeeper.domain.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface HouseRepositoryJPA extends JpaRepository<House, Long> {

    //for a given house,  get all the bees that are in it since a given date
    @Query("SELECT h FROM House h WHERE h.date > ?1")
    Iterable<House> findAllHousesSince(String date);

}