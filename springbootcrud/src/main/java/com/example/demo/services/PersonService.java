package com.example.demo.services;

import com.example.demo.model.Person;

import java.util.List;


public interface PersonService {
    List<Person> getPersons();
    Person getPersonById(Long id );
    Person  insert(Person person);
    void updatePerson(Long id , Person person);
    void deletePerson(Long id);
}
