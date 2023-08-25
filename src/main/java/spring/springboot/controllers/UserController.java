package spring.springboot.controllers;

import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import spring.springboot.constants.MsgResponse;
import spring.springboot.constants.NameApi;
import spring.springboot.dtos.UserDto;
import spring.springboot.exceptions.ServerError;
import spring.springboot.services.UserService;
import spring.springboot.utils.ResponseController;
import spring.springboot.validates.HandleValidateFields;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@RestController
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    ModelMapper modelMapper;

    ServerError exception = new ServerError();
    ResponseController res = new ResponseController();
    HandleValidateFields validate = new HandleValidateFields();

    @GetMapping(NameApi.getListUser)
    public Map<String, Object> getListUser(Map<String, String> query) {
        try {
            String limit = query.get("limit");
            String page = query.get("page");
            String searchKey = query.get("searchKey");
            Map<String, Object> results = userService.getListUsers(limit, page, searchKey);
            return res.responseResult(results, MsgResponse.getListUser);
        } catch (Exception e) {
            return exception.interval();
        }
    }

    @PostMapping(NameApi.createUser)
    public Map<String, Object> createUser(@Valid @RequestBody UserDto dto) {
        try {
            return res.responseResult(dto, null);
        } catch (Exception e) {
            return exception.interval();
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleFields(MethodArgumentNotValidException ex) {
        return validate.validateFields(ex);
    }
    
}
