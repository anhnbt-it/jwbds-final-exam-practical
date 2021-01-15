package com.codegym.springbootexam.service;

import com.codegym.springbootexam.model.Country;

public interface CountryService {

    Iterable<Country> findAll();

}
