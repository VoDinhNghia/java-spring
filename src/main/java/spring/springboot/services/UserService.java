package spring.springboot.services;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.springboot.entities.UserEntity;
import spring.springboot.repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public UserEntity createUser(UserEntity user) {
        UserEntity result = userRepository.save(user);
        return result;
    }

    public Boolean updateUser(UserEntity user) {
        userRepository.save(user);
        return true;
    }

    public UserEntity findByEmail(String email) {
        UserEntity result = userRepository.findByEmail(email);
        return result;
    }

    public Map<String, Object> getListUsers(String limit, String page, String searchKey) {
        List<UserEntity> findAll;
        long total = userRepository.findAll().stream().count();
        long skip = 0;
        long numberLimit = total;
        if (limit != null && page != null) {
            numberLimit = Integer.parseInt(limit);
            skip = (Integer.parseInt(page) - 1) * numberLimit;
        }
        if (searchKey != null) {
            findAll = userRepository.search(searchKey);
        } else {
            findAll = userRepository.findAll();
        }
        List<UserEntity> results = findAll.stream().skip(skip).limit(numberLimit).collect(Collectors.toList());
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("results", results);
        data.put("total", total);
        return data;
    }
}
