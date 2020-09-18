package com.chat.controller.photo;

import com.chat.service.file.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class PhotoRestController {

     @Autowired
     private PhotoService service;

     @PostMapping("/upload")
     public void uploadFile(@RequestBody MultipartFile file) throws IOException {
          service.createPhoto(file.getBytes(),file.getName(),file.getContentType());
     }


}
