package spring.springboot.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import spring.springboot.constants.HttpStatusCode;
import spring.springboot.constants.Constants;

public class ResponseApi {
    public ResponseEntity<Map<String, Object>> resResult(Object data, String message) {
        Map<String, Object> res = this.resObj(data, message, HttpStatusCode.OK);
        return ResponseEntity.ok().body(res);
    }

    public Map<String, Object> resObj(Object data, String message, int statusCode) {
        HashMap<String, Object> res = new HashMap<String, Object>();
        res.put(Constants.statusCode, statusCode);
        res.put(Constants.data, data);
        res.put(Constants.message, message);
        return res;
    }
}
