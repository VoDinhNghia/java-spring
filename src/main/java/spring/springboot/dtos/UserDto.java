package spring.springboot.dtos;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto {
    @NotEmpty
    @Email(message = "Email is not valid!", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;

    @NotEmpty
    @Size(max = 100, message = "Name cannot exceed 100 characters!")
    private String name;

    @NotEmpty
    @Size(min = 6, message = "Password should be at least 6 characters!")
    private String password;

    public void setUserAuthorities(List<GrantedAuthority> authorities){};
}
