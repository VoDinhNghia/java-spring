package spring.springboot.utils;

import java.util.HashMap;
import java.util.Map;

import spring.springboot.constants.HttpStatusCode;

public class ResponseController {
    public Map<String, Object> responseResult(Object data, String message) {
        HashMap<String, Object> response = new HashMap<String, Object>();
        response.put("statusCode", HttpStatusCode.OK);
        response.put("data", data);
        response.put("message", message);
        return response;
    }
}
