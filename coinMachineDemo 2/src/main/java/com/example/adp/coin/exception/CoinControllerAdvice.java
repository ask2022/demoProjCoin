package com.example.adp.coin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class CoinControllerAdvice {


    @ExceptionHandler(value = {CoinMachineException.class})
    public ResponseEntity<Object> handleCityNotFoundException(
            CoinMachineException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("message", "Bad Request, Input is invalid");

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

}
