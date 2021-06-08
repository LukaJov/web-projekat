package com.example.demo.repository;

import com.example.demo.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


@Repository
public interface AdminRepository extends  JpaRepository<Admin, Long> {
    public Admin findByUsername(String username);
}
