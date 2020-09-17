package com.chat.controller.exception;

import com.chat.exceptions.UserNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionRestController {

   @ExceptionHandler(value= UserNotFoundException.class)
   public String callUserNotFoundException() {
       return "user not found";
   }

}
