package spring.springboot.controllers;

import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import spring.springboot.constants.MsgResponse;
import spring.springboot.constants.NameApi;
import spring.springboot.dtos.ProductDto;
import spring.springboot.entities.EsProductEntity;
import spring.springboot.entities.ProductEntity;
import spring.springboot.exceptions.GlobalExceptionHandler;
import spring.springboot.services.EsProductService;
import spring.springboot.services.ProductService;
import spring.springboot.utils.ResponseApi;
import spring.springboot.validates.HandleValidateFields;

@RestController
public class ProductController {
    @Autowired
    ProductService service;

    @Autowired
    EsProductService elasticService;

    @Autowired
    ModelMapper modelMapper;

    GlobalExceptionHandler ex = new GlobalExceptionHandler();
    ResponseApi res = new ResponseApi();
    HandleValidateFields validate = new HandleValidateFields();

    @PostMapping(NameApi.createProduct)
    public ResponseEntity<Map<String, Object>> createCategory(@Valid @RequestBody ProductDto dto) {
        try {
            System.out.println("enenenen: " + dto);
            ProductEntity entity = modelMapper.map(dto, ProductEntity.class);
            ProductEntity result = service.createProduct(entity);
            elasticService.createDocProduct(result);
            return res.resResult(result, MsgResponse.createProduct);
        } catch (Exception e) {
            return ex.serverInterval();
        }
    }

    @GetMapping(NameApi.getEls)
    public ResponseEntity<Map<String, Object>> getListProducts() {
        try {
            Iterable<EsProductEntity> results = elasticService.findAllProduct();
            return res.resResult(results.iterator(), MsgResponse.getListProduct);
        } catch (Exception e) {
            return ex.serverInterval();
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleFiels(MethodArgumentNotValidException ex) {
        return validate.validateFields(ex);
    }
}
