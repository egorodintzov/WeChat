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

   @ExceptionHandler(value = UserAlReadyExistsException.class)
   public String callUserAlReadyExistsException() {
       return "user already exists";
   }

   @ExceptionHandler(value = ChatNotFoundException.class)
   public String callChatNotFoundException() {
       return "chat not found exception";
   }

   @ExceptionHandler(value = MessageNotFoundException.class)
   public String callMessageNotFoundException() {
       return "message not found";
   }

}
