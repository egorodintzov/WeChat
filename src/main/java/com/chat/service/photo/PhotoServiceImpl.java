package com.chat.service.photo;

import com.chat.dao.PhotoDao;
import com.chat.model.Photo;
import com.chat.model.User;
import com.chat.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author Egor Odintsov
 */

@Service
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private PhotoDao dao;

    @Autowired
    private UserService userService;

    /**
     * create photo
     * @param file - multipartfile object
     * @throws IOException
     */

    @Override
    public void createPhoto(MultipartFile file) throws IOException {
        // get current user by login
        User user = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());

        // create photo
        Photo photo = new Photo(file.getBytes(),file.getName(),file.getContentType());
        // set user for photo
        photo.setUser(user);

        // set photo for user
        user.setPhoto(photo);
        // and update this user
        userService.updatePhoto(user);

        dao.save(photo);
    }


}
