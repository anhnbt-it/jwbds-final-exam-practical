package com.codegym.springbootexam.controller;

import com.codegym.springbootexam.model.Country;
import com.codegym.springbootexam.service.CityService;
import com.codegym.springbootexam.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("cities")
public class CityController {

    @Autowired
    private CountryService countryService;

    @Autowired
    private CityService cityService;

    @ModelAttribute("countries")
    public Iterable<Country> countries() {
        return countryService.findAll();
    }

    @GetMapping
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("cities/index");
        modelAndView.addObject("cities", cityService.findAll());
        return modelAndView;
    }

}
