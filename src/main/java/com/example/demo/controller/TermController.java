package com.example.demo.controller;

import com.example.demo.model.Term;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.TermService;

import java.util.List;


@Controller
public class TermController {


    @Autowired
    private TermService termService;

}