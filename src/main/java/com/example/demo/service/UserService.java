package com.example.demo.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User save(User user)
    {
        return this.userRepository.save(user);
    }

    public User findByUsername(String username)
    {
        return this.userRepository.findByUsername(username);
    }
    public Optional<User> findById(Long id){return this.userRepository.findById(id);}


}