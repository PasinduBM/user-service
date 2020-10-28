package com.bawa.projectmanaement.exception.handler;

import com.bawa.projectmanaement.exception.MaintananceExeption;
import com.bawa.projectmanaement.exception.OrderNotValidExeption;
import com.bawa.projectmanaement.exception.response.MaintananceExeptionResponce;
import com.bawa.projectmanaement.exception.response.OrderNotValidExeptionResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class ExeptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Object> handleProjectIDNotUniqException(OrderNotValidExeption ex, WebRequest request){
        OrderNotValidExeptionResponse OrderNotValidExeptionResponse = new OrderNotValidExeptionResponse(ex.getMessage());
        return handleExceptionInternal(ex, OrderNotValidExeptionResponse,new HttpHeaders(),HttpStatus.BAD_REQUEST,request);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleMaintananceExeption(MaintananceExeption ex, WebRequest request){
        MaintananceExeptionResponce MaintananceExeptionResponce = new MaintananceExeptionResponce(ex.getMessage());
        return handleExceptionInternal(ex, MaintananceExeptionResponce,new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR,request);
    }
}
