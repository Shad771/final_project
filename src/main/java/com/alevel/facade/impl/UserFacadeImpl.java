package com.alevel.facade.impl;

import com.alevel.facade.UserFacade;
import com.alevel.service.UserCrudService;
import com.alevel.web.dto.request.UserRequestDto;
import com.alevel.web.dto.response.PageData;
import com.alevel.web.dto.response.UserResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

@Service
public class UserFacadeImpl implements UserFacade {
     private final UserCrudService userService;

    public UserFacadeImpl(UserCrudService userService) {
        this.userService = userService;
    }

    @Override
    public void create(UserRequestDto userRequestDto) {

    }

    @Override
    public void update(UserRequestDto userRequestDto, Long id) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public UserResponseDto findById(Long id) {
        return null;
    }

    @Override
    public PageData<UserResponseDto> findAll(WebRequest request) {
        return null;
    }

    @Override
    public Long findByEmail(String email) {
        return userService.findByEmail(email);
    }
}
