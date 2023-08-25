package spring.springboot.configurations;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthencationProvider implements AuthenticationProvider {
    private final Log logger = LogFactory.getLog(getClass());

    @Autowired
    CustomUserDetailService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken authToken = null;
        if (authentication == null) {
            return null;
        }
        String email = String.valueOf(authentication.getName());
        String password = String.valueOf(authentication.getCredentials());
        logger.info("Username: " + email + " password: " + password);
        User user = (User) userService.loadUserByUsername(email);
        if (user == null) {
            throw new UsernameNotFoundException("User name not found");
        }
        if (user.getPassword().equals(password)) {
            authToken = new UsernamePasswordAuthenticationToken(user.getUsername(), null, user.getAuthorities());
        }
        return authToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
