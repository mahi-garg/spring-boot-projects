package com.example.demo.api;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("api/v1/person")
@RestController
public class PersonController {
    private PersonService personService;
    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }
    @PostMapping
    public void addPerson(@Validated @NonNull @RequestBody  Person person){
         personService.addPerson(person);
    }

    @GetMapping
    public List<Person> getPerson(){
        return personService.getPerson();
    }

    @GetMapping(path = "/{id}")
    public Person getPersonById(@PathVariable("id") UUID id){
        return personService.getPersonById(id).orElse(null);
    }

    @DeleteMapping(path = "/{id}")
    public int deletePersonById(@PathVariable("id") UUID id){
     return personService.deletePersonById(id);
    }

    @PutMapping(path = "/{id}")
    public int updatePersonById(@PathVariable("id") UUID id,@NonNull @Validated @RequestBody Person person){
        return personService.updatePersonById( id, person);
    }
}
