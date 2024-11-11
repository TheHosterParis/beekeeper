package com.yvan.beekeeper.domain;

import java.util.Optional;

public interface PersonRepository {

    Optional<Person> findById(Long id);

    void deleteById(Long id);

    Iterable<Person> findAll();

    void save(Person person);

    Person findRandomPerson();

    Iterable<Person> findAllPersonsSince(String date);
}