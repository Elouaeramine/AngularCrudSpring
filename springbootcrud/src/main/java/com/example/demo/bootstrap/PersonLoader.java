package com.example.demo.bootstrap;

import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class PersonLoader implements CommandLineRunner {
    public final PersonRepository personRepository;

    public PersonLoader(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    @Override
    public void run(String... args ) throws Exception{
        loadPersons();
    }

    private void loadPersons(){
        if(personRepository.count() == 0) {
            personRepository.save(
                    Person.builder()
                            .firstName("Amine")
                            .lastName("Elouaer")
                            .age(22)
                            .build()
            );
            personRepository.save(
                    Person.builder()
                            .firstName("Manuel")
                            .lastName("Schliter")
                            .age(25)
                            .build()
            );
            System.out.println("Loaded !");
        }
    }
}
