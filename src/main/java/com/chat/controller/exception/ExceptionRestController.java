package com.chat.controller.exception;

import com.chat.exceptions.*;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionRestController {

   @ExceptionHandler(value = UserNotFoundException.class)
   public String callUserNotFoundException() {
       return "user not found";
   }

   @ExceptionHandler(value = WrongLoginOrPasswordException.class)
   public String callWrongLoginOrPassword() {
       return "wrong login or password";
   }

   @ExceptionHandler(value = NoPhotoException.class)
   public String callNoPhotoException() {
       return "no photo";
   }

   @ExceptionHandler(value = UserAllReadyExistsException.class)
   public String callUserAllReadyExistsException() {
       return "user all ready exists";
   }

   @ExceptionHandler(value = ChatNotFoundException.class)
   public String callChatNotFoundException() {
       return "chat not found exception";
   }

}
