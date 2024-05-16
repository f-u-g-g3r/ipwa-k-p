package com.ipwa.kp.advices;

import com.ipwa.kp.controllers.exceptions.TeacherNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class TeacherNotFoundAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(TeacherNotFoundException.class)
    protected ResponseEntity<Object> teacherNotFoundHandler(TeacherNotFoundException e, WebRequest request) {
        ErrorResponse response = new ErrorResponse(e.getMessage());
        return handleExceptionInternal(e, response, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}
