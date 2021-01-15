package com.codegym.springbootexam.service.impl;

import com.codegym.springbootexam.model.Country;
import com.codegym.springbootexam.repository.CountryRepository;
import com.codegym.springbootexam.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public Iterable<Country> findAll() {
        return countryRepository.findAll();
    }
}
