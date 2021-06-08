package com.example.demo.service;

import java.util.Date;
import java.util.List;

import com.example.demo.model.Admin;
import com.example.demo.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    public Admin findByUsername(String username){
        return this.adminRepository.findByUsername(username);
    }
}