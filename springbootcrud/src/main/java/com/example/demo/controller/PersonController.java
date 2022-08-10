package com.example.demo.controller;

import com.example.demo.model.Person;
import com.example.demo.services.PersonService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person")
public class PersonController {

    PersonService personService;

    public PersonController(PersonService personService){
        this.personService= personService;
    }


    // This Function receives a GET Request
    // This Function will give back a list of all persons in our database
    @GetMapping
    @CrossOrigin
    public ResponseEntity<List<Person>> getAllPersons() {
        List<Person> persons = personService.getPersons();
        return new ResponseEntity<>(persons , HttpStatus.OK);
    }

    // This Function receives a GET Request
    // This Functino will give back a Person depending on the id

    @GetMapping({"/{personID}"})
    @CrossOrigin
    public ResponseEntity<Person> getPerson(@PathVariable Long personID){
        return new ResponseEntity<>(personService.getPersonById(personID), HttpStatus.OK);
    }

    // This Fucntion receives a POST request
    // This Function will create a new Person in our database
    @PostMapping
    @CrossOrigin
    public ResponseEntity<Person> savePerson(@RequestBody Person person){
        Person person1 = personService.insert(person);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("person" , "/api/person" + person1.getId().toString());
        return  new ResponseEntity<>(person1 , httpHeaders, HttpStatus.CREATED);

    }

    @PutMapping({"/{personID}"})
    @CrossOrigin
    public ResponseEntity<Person> updatePerson(@PathVariable("personID") Long id, @RequestBody Person person ){
        personService.updatePerson(id ,person);
        return  new ResponseEntity<>(personService.getPersonById(id), HttpStatus.OK);
    }

    @DeleteMapping({"/{personID}"})
    @CrossOrigin
    public ResponseEntity<Person> deletePerson(@PathVariable("personID") Long id ){
        personService.deletePerson(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
