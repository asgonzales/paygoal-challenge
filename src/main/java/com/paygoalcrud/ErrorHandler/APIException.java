
package com.paygoalcrud.ErrorHandler;

import org.springframework.http.HttpStatus;


public class APIException extends Exception {
    private HttpStatus httpStatus;
    public APIException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
    
}
