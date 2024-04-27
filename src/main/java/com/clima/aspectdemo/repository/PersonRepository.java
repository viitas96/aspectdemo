package com.clima.aspectdemo.repository;

import com.clima.aspectdemo.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {
}
