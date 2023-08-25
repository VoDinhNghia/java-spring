package spring.springboot.dtos;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;

@Data
public class LoginResponseDto {
    private String name;
    private String password;
    private String email;
    private String code;
    private List<GrantedAuthority> userAuthorities;
    private String accessToken;
}
