package com.movewise.movewise_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movewise.movewise_api.service.ServiceService;

@RestController
@RequestMapping(value = "/api/services")
public class ServiceController {

    @Autowired
    private ServiceService serviceService;
}
