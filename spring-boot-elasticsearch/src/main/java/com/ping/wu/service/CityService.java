package com.ping.wu.service;

import com.ping.wu.model.City;

import java.util.List;

/**
 * Created by ping.wu on 2018/4/28.
 */
public interface CityService {
    String save(City city);
    String listCity();
    String getCityById(Long id);
}
