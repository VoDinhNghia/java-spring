package spring.springboot.configurations;

import java.io.IOException;
import java.io.StringWriter;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import spring.springboot.constants.HttpStatusCode;

public class CustomAuthenEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
        StringWriter jsonRes = new StringWriter();
        response.setHeader("WWW-authenticate", "Basic realm=\"Access to /signin authentication endpoint\"");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        jsonRes.write("{");
        jsonRes.write("\"message\": " + "\"You are not authenticated - " + authException.getMessage() + "\",\n");
        jsonRes.write("\t\"statusCode\": " + HttpStatusCode.UN_AUTHORIZED);
        jsonRes.write("}");
        response.getWriter().write(jsonRes.toString());
    }
    
}
