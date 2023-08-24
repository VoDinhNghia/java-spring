package spring.springboot.exceptions;

import java.util.HashMap;
import java.util.Map;

import spring.springboot.constants.HttpStatusCode;
import spring.springboot.constants.MsgResponse;

public class ServerError {
    public Map<String, Object> interval() {
        HashMap<String, Object> response = new HashMap<String, Object>();
        response.put("statusCode", HttpStatusCode.SERVER_INTERVAL);
        response.put("message", MsgResponse.serverInterval);
        return response;
    }
}
