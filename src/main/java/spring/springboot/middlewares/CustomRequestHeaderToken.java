package spring.springboot.middlewares;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import spring.springboot.constants.MsgResponse;
import spring.springboot.constants.SecurityConstant;
import spring.springboot.utils.JwtToken;

@Component
public class CustomRequestHeaderToken extends OncePerRequestFilter {
    @Autowired
    CustomUserDetailService userService;

    private JwtToken jwtToken = new JwtToken();
    private final Log logger = LogFactory.getLog(getClass());

    public CustomRequestHeaderToken(AuthenticationManager authManager) {
        Assert.notNull(authManager, MsgResponse.authNotNull);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String email = "";
        UsernamePasswordAuthenticationToken authencation;
        String uri = request.getRequestURI();
        logger.info("Request uri: " + uri);
        try {
            String headerToken = "";
            headerToken = request.getHeader(SecurityConstant.authorization);
            if (headerToken == null || !headerToken.startsWith(SecurityConstant.prefixBearToken)) {
                filterChain.doFilter(request, response);
                return;
            }

            if (headerToken.startsWith(SecurityConstant.prefixBearToken)) {
                headerToken = StringUtils.delete(headerToken, SecurityConstant.prefixBearToken).trim();
                if (jwtToken.isTokenValid(headerToken)) {
                    email = jwtToken.getEmailFromToken(headerToken);
                    User user = (User) userService.loadUserByUsername(email);
                    if (user != null) {
                        authencation = new UsernamePasswordAuthenticationToken(email, null, user.getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(authencation);
                        logger.info("Authentication: " + authencation.toString());
                    }
                }
            }
        } catch (AuthenticationException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
        response.setHeader("Access-Control-Allow-Origin", "*");
        filterChain.doFilter(request, response);
    }
    
}
