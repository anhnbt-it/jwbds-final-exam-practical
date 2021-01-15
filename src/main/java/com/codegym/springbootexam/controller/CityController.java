package com.codegym.springbootexam.controller;

import com.codegym.springbootexam.model.City;
import com.codegym.springbootexam.model.Country;
import com.codegym.springbootexam.service.CityService;
import com.codegym.springbootexam.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("create")
    public String create(Model model) {
        model.addAttribute("city", new City());
        return "cities/create";
    }

    @PostMapping("create")
    public String create(@Validated @ModelAttribute("city") City city, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return "cities/create";
        }
        cityService.save(city);
        redirect.addFlashAttribute("message", "Thêm thành phố " + city.getName() + " thành công!");
        return "redirect:/cities";
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

    @GetMapping("edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model, RedirectAttributes redirect) {
        Optional<City> city = cityService.findById(id);
        if (!city.isPresent()) {
            redirect.addFlashAttribute("message", "Không tìm thấy city có id " + id);
            return "redirect:/cities";
        }
        model.addAttribute("city", city.get());
        return "cities/edit";
    }

    @PostMapping("edit")
    public String edit(@Validated @ModelAttribute("city") City city, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return "cities/edit";
        }
        cityService.save(city);
        redirect.addFlashAttribute("message", "Cập nhật thành phố " + city.getName() + " thành công!");
        return "redirect:/cities";
    }

}
