package spring.springboot.controllers;

import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import spring.springboot.constants.Constants;
import spring.springboot.constants.MsgResponse;
import spring.springboot.constants.NameApi;
import spring.springboot.dtos.CategoryDto;
import spring.springboot.entities.CategoryEntity;
import spring.springboot.entities.ElasticEntity;
import spring.springboot.services.CategoryService;
import spring.springboot.utils.ResponseApi;
import spring.springboot.validates.HandleValidateFields;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import spring.springboot.exceptions.GlobalExceptionHandler;
import spring.springboot.repositories.ElasticRepository;

import org.springframework.http.ResponseEntity;

@RestController
public class CategoryController {
    @Autowired
    CategoryService cateService;

    @Autowired
    ElasticRepository esRepo;

    @Autowired
    ModelMapper modelMapper;

    GlobalExceptionHandler ex = new GlobalExceptionHandler();
    ResponseApi res = new ResponseApi();
    HandleValidateFields validate = new HandleValidateFields();

    @PostMapping(NameApi.createCategory)
    public ResponseEntity<Map<String, Object>> createCategory(@Valid @RequestBody CategoryDto dto) {
        try {
            CategoryEntity entity = modelMapper.map(dto, CategoryEntity.class);
            CategoryEntity result = cateService.createCategory(entity);
            ElasticEntity esEn = modelMapper.map(dto, ElasticEntity.class);
            esRepo.save(esEn);
            return res.resResult(result, MsgResponse.createCategory);
        } catch (Exception e) {
            return ex.serverInterval();
        }
    }

    @GetMapping(NameApi.getListCategories)
    public ResponseEntity<Map<String, Object>> getListCategorys(@RequestParam Map<String, String> query) {
        try {
            String limit = query.get(Constants.queryLimit);
            String page = query.get(Constants.queryPage);
            String searchKey = query.get(Constants.querySearchKey);
            Map<String, Object> results = cateService.listCategories(limit, page, searchKey);
            return res.resResult(results, MsgResponse.getListCategories);
        } catch (Exception e) {
            return ex.serverInterval();
        }
    }

    @PostMapping(NameApi.createEls)
    public ResponseEntity<Map<String, Object>> testCreateDocsElas(@Valid @RequestBody ElasticEntity dto) {
        try {
            ElasticEntity result = esRepo.save(dto);
            return res.resResult(result, MsgResponse.createCategory);
        } catch (Exception e) {
            return ex.serverInterval();
        }
    }

    @GetMapping(NameApi.getEls)
    public ResponseEntity<Map<String, Object>> testGetElastic() {
        try {
            Iterable<ElasticEntity> test = esRepo.findAll();
            return res.resResult(test.iterator(), MsgResponse.getListCategories);
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
