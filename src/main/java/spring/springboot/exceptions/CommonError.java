package spring.springboot.exceptions;

import java.util.HashMap;
import java.util.Map;

public class CommonError {
    public Map<String, Object> response(int statusCode, String message) {
        HashMap<String, Object> res = new HashMap<String, Object>();
        res.put("statusCode", statusCode);
        res.put("message", message);
        return res;
    }
}
