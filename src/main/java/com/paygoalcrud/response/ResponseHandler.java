
package com.paygoalcrud.response;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class ResponseHandler {
     public static ResponseEntity success (HttpStatus httpStatus, Object data) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("httpStatus", httpStatus);
        response.put("data", data);
        return new ResponseEntity(response, httpStatus);
    }
    public static ResponseEntity success (HttpStatus httpStatus, Object data, HttpHeaders headers) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("httpStatus", httpStatus);
        response.put("data", data);
        return new ResponseEntity(response, headers, httpStatus);
    }
    
    public static ResponseEntity failed (HttpStatus httpStatus, Object data) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", false);
        response.put("httpStatus", httpStatus);
        response.put("message", data);
        return new ResponseEntity(response, httpStatus);
    }
    public static ResponseEntity failed (HttpStatus httpStatus) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", false);
        response.put("httpStatus", httpStatus);
        return new ResponseEntity(response, httpStatus);
    }
}
