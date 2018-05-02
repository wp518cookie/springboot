package com.ping.wu.controller;

import com.ping.wu.model.City;
import com.ping.wu.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by ping.wu on 2018/4/28.
 */
@RestController
@RequestMapping("/city")
public class CityController {
    @Autowired
    private CityService cityService;

    @PutMapping
    public String addCity(@RequestBody City city) {
        return cityService.save(city).toString();
    }

    @GetMapping(path = "/{id}")
    public String getCityById(@PathVariable Long id) {
        return cityService.getCityById(id);
    }

    @GetMapping
    public String listCity() {
        return cityService.listCity();
    }
}
