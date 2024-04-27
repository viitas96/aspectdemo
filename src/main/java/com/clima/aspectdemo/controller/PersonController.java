package com.clima.aspectdemo.controller;

import com.clima.aspectdemo.entity.Person;
import com.clima.aspectdemo.service.PersonService;
import com.clima.aspectdemo.util.CheckLevelPermission;
import com.clima.aspectdemo.util.Module;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/persons")
public class PersonController {

    private final PersonService personService;

    @GetMapping
    public List<Person> getAllPersons() throws Exception {
        return personService.readFromFile();
    }

    @GetMapping("/{id}")
    @CheckLevelPermission(Module.PERSONS)
    public Person getPersonById(@PathVariable int id) {

        return personService.getById(id);
    }

    @PutMapping("/{id}")
    public Person updatePerson(
            @RequestBody Person person,
            @PathVariable int id) {

        return personService.updatePerson(person, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        personService.deleteById(id);
    }

    @PostMapping
    public Person createPerson(@RequestBody Person person) {
        return personService.createPerson(person);
    }

}
