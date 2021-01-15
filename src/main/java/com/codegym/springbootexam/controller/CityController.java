package com.codegym.springbootexam.controller;

import com.codegym.springbootexam.model.City;
import com.codegym.springbootexam.model.Country;
import com.codegym.springbootexam.service.CityService;
import com.codegym.springbootexam.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

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

    @GetMapping("{id}")
    public String showDetails(@PathVariable("id") Long id, Model model, RedirectAttributes redirect) {
        Optional<City> city = cityService.findById(id);
        if (!city.isPresent()) {
            redirect.addFlashAttribute("message", "Không tìm thấy city có id " + id);
            return "redirect:/cities";
        }
        model.addAttribute("city", city.get());
        return "cities/showDetails";
    }



}
