package com.clima.aspectdemo.service;

import com.clima.aspectdemo.entity.Person;
import com.clima.aspectdemo.repository.PersonRepository;
import com.clima.aspectdemo.util.LogExecutionTime;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final ObjectMapper objectMapper;
    private final PersonRepository personRepository;

    @LogExecutionTime("reading from file")
    public List<Person> readFromFile() throws Exception {
        File file = new File("src/main/resources/data.json");

        return objectMapper.readValue(file, new TypeReference<>() {});
    }

    public Person getById(int id) {
        return Person.builder()
                .id(id)
                .age(18)
                .email("tempmail@gmail.com")
                .firstName("John")
                .lastName("Smith")
                .idNumber(999888)
                .build();
    }

    public Person updatePerson(Person person, int id) {
        person.setId(id);
        return person;
    }

    public void deleteById(int id) {
        personRepository.deleteById(id);
    }

    public Person createPerson(Person person) {
        return personRepository.save(person);
    }
}
