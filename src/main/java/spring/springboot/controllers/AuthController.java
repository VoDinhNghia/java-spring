package spring.springboot.controllers;

import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import spring.springboot.exceptions.CommonError;
import spring.springboot.exceptions.ServerError;
import spring.springboot.services.UserService;
import spring.springboot.utils.CryptoPassword;
import spring.springboot.utils.JwtToken;
import spring.springboot.utils.ResponseController;
import spring.springboot.validates.HandleValidateFields;
import org.springframework.web.bind.annotation.GetMapping;
import spring.springboot.constants.SecurityConstant;

@RestController
public class AuthController {
    @Autowired
    UserService userService;

    @Autowired
    ModelMapper modelMapper;

    HandleValidateFields validate = new HandleValidateFields();
    ResponseController res = new ResponseController();
    CommonError error = new CommonError();
    ServerError exception = new ServerError();
    JwtToken jwtToken = new JwtToken();
    CryptoPassword cryptoPassword = new CryptoPassword();

    @PostMapping(NameApi.authLogin)
    public Map<String, Object> login(@Valid @RequestBody LoginDto dto) {
        try {
            UserEntity user = userService.findByEmail(dto.getEmail());
            if (user == null) {
                return error.response(HttpStatusCode.NOT_FOUND, MsgResponse.userNotFound);
            }
            String pass = cryptoPassword.endCode(dto.getPassword());
            String userPass = user.getPassword();
            if (!userPass.equals(pass)) {
                return error.response(HttpStatusCode.UN_AUTHORIZED, MsgResponse.unAuthorized);
            }
            LoginResponseDto response = modelMapper.map(user, LoginResponseDto.class);
            response.setAccessToken(jwtToken.generateJwtToken(dto.getEmail()));
            return res.responseResult(response, MsgResponse.authLogin);
        } catch (Exception e) {
            return exception.interval();
        }
    }

    @GetMapping(NameApi.getMe)
    public Map<String, Object> getMe(HttpServletRequest req) {
        try {
            String headerReq = req.getHeader(SecurityConstant.authorization);
            String accessToken = StringUtils.delete(headerReq, SecurityConstant.prefixBearToken).trim();
            String email = jwtToken.getEmailFromToken(accessToken);
            UserEntity user = userService.findByEmail(email);
            return res.responseResult(user, MsgResponse.getMe);
        } catch (Exception e) {
            return exception.interval();
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidateFields(MethodArgumentNotValidException ex) {
        return validate.validateFields(ex);
    }
}
