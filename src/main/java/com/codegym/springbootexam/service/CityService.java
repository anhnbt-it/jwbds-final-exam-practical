package com.codegym.springbootexam.service;

import com.codegym.springbootexam.model.City;

import java.util.Optional;

public interface CityService {

    Iterable<City> findAll();

    Optional<City> findById(Long id);
}
