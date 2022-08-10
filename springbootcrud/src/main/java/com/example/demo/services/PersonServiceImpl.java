package com.example.demo.services;

import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;
import com.example.demo.services.PersonService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {
    PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> getPersons(){
        List<Person> persons = new ArrayList<>();
        personRepository.findAll().forEach(persons::add);
        return persons;
    }

    @Override
    public Person getPersonById(Long id ) {
        return personRepository.findById(id).get();
    }

    @Override
    public Person insert(Person person) {
        return personRepository.save(person);
    }

    @Override
    public void updatePerson(Long id, Person person) {
        Person personFromDB = personRepository.findById(id).get();
        System.out.println(personFromDB.toString());
        personFromDB.setFirstName(person.getFirstName());
        personFromDB.setLastName(person.getLastName());
        personFromDB.setAge(person.getAge());
        personRepository.save(personFromDB);
    }

    @Override
    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }
}