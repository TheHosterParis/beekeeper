package com.yvan.beekeeper.infrastructure;

import com.yvan.beekeeper.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface PersonRepositoryJPA extends JpaRepository<Person, Long> {

    //for a given person,  get all the bees that are in it since a given date
    @Query(value = "SELECT * FROM person WHERE date > ?1", nativeQuery = true)
    Iterable<Person> findAllPersonsSince(String date);

    @Query(value = "SELECT * FROM person ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    Person findRandomPerson();
}