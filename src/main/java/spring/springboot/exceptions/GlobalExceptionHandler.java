package spring.springboot.exceptions;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import spring.springboot.constants.HttpStatusCode;
import spring.springboot.constants.MsgResponse;
import spring.springboot.utils.ResponseApi;

@ControllerAdvice
public class GlobalExceptionHandler {
    ResponseApi resApi = new ResponseApi();

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> serverInterval() {
        Map<String, Object> res = resApi.resObj(null, MsgResponse.serverInterval, HttpStatusCode.SERVER_INTERVAL);
        return ResponseEntity.internalServerError().body(res);
    }

    public ResponseEntity<Map<String, Object>> babReq(int statusCode, String message) {
        Map<String, Object> res = resApi.resObj(null, message, statusCode);
        return ResponseEntity.badRequest().body(res);
    }

    public ResponseEntity<Map<String, Object>> conflict(int statusCode, String message) {
        Map<String, Object> res = resApi.resObj(null, message, statusCode);
        return ResponseEntity.status(HttpStatusCode.CONFLICT).body(res);
    }

    public ResponseEntity<Map<String, Object>> notFound(int statusCode, String message) {
        Map<String, Object> res = resApi.resObj(null, message, statusCode);
        return ResponseEntity.status(HttpStatusCode.NOT_FOUND).body(res);
    }

    public ResponseEntity<Map<String, Object>> unAuthen(int statusCode, String message) {
        Map<String, Object> res = resApi.resObj(null, message, statusCode);
        return ResponseEntity.status(HttpStatusCode.UN_AUTHORIZED).body(res);
    }
}
