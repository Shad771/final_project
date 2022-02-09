package com.alevel.facade;

import com.alevel.web.dto.request.UserRequestDto;
import com.alevel.web.dto.response.UserResponseDto;

public interface UserFacade extends CrudFacade<UserRequestDto, UserResponseDto>{

    Long findByEmail(String email);
}
