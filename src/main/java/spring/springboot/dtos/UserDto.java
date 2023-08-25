package spring.springboot.dtos;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserDto {
    @NotEmpty
    private String email;

    @NotEmpty
    private String name;

    @NotEmpty
    private String password;

    public void setUserAuthorities(List<GrantedAuthority> authorities){};
}
