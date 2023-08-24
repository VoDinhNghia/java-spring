package spring.springboot.dtos;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserDto {
    @NotEmpty
    private String email;

    private String name;

    private String password;

    public void setUserAuthorities(List<GrantedAuthority> authorities){};
}
