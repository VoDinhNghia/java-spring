package spring.springboot.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import spring.springboot.constants.MsgResponse;
import spring.springboot.constants.NameApi;
import spring.springboot.constants.SecurityConstant;
import spring.springboot.dtos.UserDto;
import spring.springboot.entities.UserEntity;
import spring.springboot.exceptions.GlobalExceptionHandler;
import spring.springboot.services.UserService;
import spring.springboot.utils.CryptoPassword;
import spring.springboot.utils.GenerateUserCode;
import spring.springboot.utils.ResponseApi;
import spring.springboot.validates.HandleValidateFields;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import spring.springboot.constants.Constants;
import spring.springboot.constants.HttpStatusCode;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    ModelMapper modelMapper;

    ResponseApi res = new ResponseApi();
    HandleValidateFields validate = new HandleValidateFields();
    CryptoPassword cryptoPassword = new CryptoPassword();
    GenerateUserCode userCode = new GenerateUserCode();
    GlobalExceptionHandler ex = new GlobalExceptionHandler();

    @GetMapping(NameApi.getListUser)
    public ResponseEntity<Map<String, Object>> getListUser(@RequestParam Map<String, String> query) {
        try {
            String limit = query.get(Constants.queryLimit);
            String page = query.get(Constants.queryPage);
            String searchKey = query.get(Constants.querySearchKey);
            Map<String, Object> results = userService.getListUsers(limit, page, searchKey);
            return res.resResult(results, MsgResponse.getListUser);
        } catch (Exception e) {
            return ex.serverInterval();
        }
    }

    @PostMapping(NameApi.createUser)
    public ResponseEntity<Map<String, Object>> createUser(@Valid @RequestBody UserDto dto) {
        try {
            UserEntity user = userService.findByEmail(dto.getEmail());
            if (user != null) {
                return ex.conflict(HttpStatusCode.CONFLICT, MsgResponse.emailExisted);
            }
            UserEntity entity = modelMapper.map(dto, UserEntity.class);
            entity.setPassword(cryptoPassword.endCode(dto.getPassword()));
            entity.setCode(userCode.createCode());
            entity.setUserAuthorities(
                userService.getAuthorities(new ArrayList<String>(Arrays.asList(new String[] {
                    SecurityConstant.userRole
                })))
            );
            UserEntity result = userService.createUser(entity);
            return res.resResult(result, MsgResponse.createUser);
        } catch (Exception e) {
            return ex.serverInterval();
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleFields(MethodArgumentNotValidException ex) {
        return validate.validateFields(ex);
    }

}
