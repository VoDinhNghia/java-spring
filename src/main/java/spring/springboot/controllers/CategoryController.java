package spring.springboot.controllers;

import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import spring.springboot.constants.MsgResponse;
import spring.springboot.constants.NameApi;
import spring.springboot.dtos.CategoryDto;
import spring.springboot.entities.CategoryEntity;
import spring.springboot.exceptions.CommonError;
import spring.springboot.exceptions.ServerError;
import spring.springboot.services.CategoryService;
import spring.springboot.utils.ResponseController;
import spring.springboot.validates.HandleValidateFields;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
public class CategoryController {
    @Autowired
    CategoryService cateService;

    @Autowired
    ModelMapper modelMapper;

    ServerError exception = new ServerError();
    ResponseController res = new ResponseController();
    CommonError error = new CommonError();
    HandleValidateFields validate = new HandleValidateFields();

    @PostMapping(NameApi.createCategory)
    public Map<String, Object> createCategory(@Valid @RequestBody CategoryDto dto) {
        try {
            CategoryEntity entity = modelMapper.map(dto, CategoryEntity.class);
            CategoryEntity result = cateService.createCategory(entity);
            return res.responseResult(result, MsgResponse.createCategory);
        } catch (Exception e) {
            return exception.interval();
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleFiels(MethodArgumentNotValidException ex) {
        return validate.validateFields(ex);
    }
}
