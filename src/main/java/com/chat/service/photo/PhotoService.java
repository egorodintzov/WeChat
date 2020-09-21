package com.chat.service.photo;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PhotoService {

    void createPhoto(MultipartFile file) throws IOException ;
}
