package spring.springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.springboot.entities.UserEntity;
import spring.springboot.repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public UserEntity findByEmail(String email) {
        UserEntity result = userRepository.findByEmail(email);
        return result;
    }
}
