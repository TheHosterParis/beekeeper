package com.yvan.beekeeper.infrastructure;

import com.yvan.beekeeper.domain.Person;
import com.yvan.beekeeper.domain.PersonRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PersonRepositoryImpl implements PersonRepository {

    private final PersonRepositoryJPA personRepository;

    public PersonRepositoryImpl(PersonRepositoryJPA personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Iterable<Person> findAllPersonsSince(String date) {
        return personRepository.findAllPersonsSince(date);
    }

    @Override
    public Optional<Person> findById(Long id) {
        return personRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        personRepository.deleteById(id);
    }

    @Override
    public Iterable<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public void save(Person person) {
        personRepository.save(person);
    }

    @Override
    public Person findRandomPerson() {
        return personRepository.findRandomPerson();
    }
}
