package com.codegym.springbootexam.service.impl;

import com.codegym.springbootexam.model.City;
import com.codegym.springbootexam.repository.CityRepository;
import com.codegym.springbootexam.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Override
    public Iterable<City> findAll() {
        return cityRepository.findAll();
    }
}
