package spring.springboot.controllers;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import spring.springboot.constants.MsgResponse;
import spring.springboot.constants.NameApi;
import spring.springboot.exceptions.ServerError;
import spring.springboot.utils.ResponseController;

@RestController
public class UserController {
    ServerError exception = new ServerError();
    ResponseController res = new ResponseController();

    @GetMapping(NameApi.getListUser)
    public Map<String, Object> getListUser() {
        try {
            return res.responseResult("ok", MsgResponse.getListUser);
        } catch (Exception e) {
            return exception.interval();
        }
    }
}
