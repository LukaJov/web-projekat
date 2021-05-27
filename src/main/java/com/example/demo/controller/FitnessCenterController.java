package com.example.demo.controller;

import com.example.demo.model.FitnessCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.FitnessCenterService;

import java.util.List;


@RestController
public class FitnessCenterController {

    @Autowired
    private FitnessCenterService fitnessCenterService;


}
