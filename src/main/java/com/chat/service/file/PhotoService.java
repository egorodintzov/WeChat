package com.chat.service.file;

import com.chat.dao.PhotoDao;
import com.chat.model.Photo;
import com.chat.model.User;
import com.chat.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * @author Egor Odintsov
 */

@Service
public class PhotoService {

    @Autowired
    private PhotoDao dao;

    @Autowired
    private UserService userService;

    /**
     * create photo
     * @param content
     * @param name
     * @param contentType
     */

    public void createPhoto(byte[] content,String name,String contentType) {
        // get current user by login
        User user = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());

        // create photo
        Photo photo = new Photo(content,name,contentType);
        // set user for photo
        photo.setUser(user);

        // set photo for user
        user.setPhoto(photo);
        // and update this user
        userService.updatePhoto(user);

        dao.save(photo);
    }


}
