package spring.springboot.controllers;

import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import spring.springboot.constants.HttpStatusCode;
import spring.springboot.constants.MsgResponse;
import spring.springboot.constants.NameApi;
import spring.springboot.dtos.LoginDto;
import spring.springboot.dtos.LoginResponseDto;
import spring.springboot.entities.UserEntity;
import spring.springboot.services.UserService;
import spring.springboot.utils.CryptoPassword;
import spring.springboot.utils.JwtToken;
import spring.springboot.utils.ResponseApi;
import spring.springboot.validates.HandleValidateFields;
import org.springframework.web.bind.annotation.GetMapping;
import spring.springboot.constants.SecurityConstant;
import spring.springboot.exceptions.GlobalExceptionHandler;

@RestController
public class AuthController {
    @Autowired
    UserService userService;

    @Autowired
    ModelMapper modelMapper;

    HandleValidateFields validate = new HandleValidateFields();
    ResponseApi res = new ResponseApi();
    JwtToken jwtToken = new JwtToken();
    CryptoPassword cryptoPassword = new CryptoPassword();
    GlobalExceptionHandler ex = new GlobalExceptionHandler();

    @PostMapping(NameApi.authLogin)
    public ResponseEntity<Map<String, Object>> login(@Valid @RequestBody LoginDto dto) {
        try {
            UserEntity user = userService.findByEmail(dto.getEmail());
            if (user == null) {
                return ex.notFound(HttpStatusCode.NOT_FOUND, MsgResponse.userNotFound);
            }
            String pass = cryptoPassword.endCode(dto.getPassword());
            String userPass = user.getPassword();
            if (!userPass.equals(pass)) {
                return ex.unAuthen(HttpStatusCode.UN_AUTHORIZED, MsgResponse.unAuthorized);
            }
            LoginResponseDto response = modelMapper.map(user, LoginResponseDto.class);
            response.setAccessToken(jwtToken.generateJwtToken(dto.getEmail()));
            return res.resResult(response, MsgResponse.authLogin);
        } catch (Exception e) {
            return ex.serverInterval();
        }
    }

    @GetMapping(NameApi.getMe)
    public ResponseEntity<Map<String, Object>> getMe(HttpServletRequest req) {
        try {
            String headerReq = req.getHeader(SecurityConstant.authorization);
            String accessToken = StringUtils.delete(headerReq, SecurityConstant.prefixBearToken).trim();
            String email = jwtToken.getEmailFromToken(accessToken);
            UserEntity user = userService.findByEmail(email);
            return res.resResult(user, MsgResponse.getMe);
        } catch (Exception e) {
            return ex.serverInterval();
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidateFields(MethodArgumentNotValidException ex) {
        return validate.validateFields(ex);
    }
}
