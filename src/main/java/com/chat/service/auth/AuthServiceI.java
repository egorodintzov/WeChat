package com.chat.service.auth;

import com.chat.dto.UserDto;

public interface AuthServiceI {

    boolean checkIsCreated(UserDto userDto);

}
