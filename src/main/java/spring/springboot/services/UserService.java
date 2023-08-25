package spring.springboot.services;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import spring.springboot.constants.SecurityConstant;
import spring.springboot.entities.UserEntity;
import spring.springboot.repositories.UserRepository;
import spring.springboot.utils.CryptoPassword;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    CryptoPassword cryptoPassword = new CryptoPassword();

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

    public List<GrantedAuthority> getAuthorities(List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));
        return authorities;
    }

    public CommandLineRunner initAccountAdmin() {
        try {
            String code = "Admin-spring-boot-2023";
            UserEntity admin = userRepository.findByCode(code);
            if (admin == null) {
                UserEntity user = new UserEntity();
                user.setName("Admin App");
                user.setCode(code);
                user.setPassword(cryptoPassword.endCode("admin123@"));
                user.setEmail("vodinhnghia85@gmail.com");
                user.setUserAuthorities(
                        getAuthorities(new ArrayList<String>(Arrays.asList(new String[] {
                                SecurityConstant.adminRole
                        }))));
                userRepository.save(user);
                System.out.println("Create admin success");
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
