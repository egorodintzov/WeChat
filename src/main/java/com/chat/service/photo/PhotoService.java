package com.chat.service.photo;

import com.chat.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PhotoService {

    void createPhoto(MultipartFile file, User user) throws IOException ;
}
