package service;

import java.util.Date;
import java.util.List;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User save(User user)
    {
        return this.userRepository.save(user);
    }
}
