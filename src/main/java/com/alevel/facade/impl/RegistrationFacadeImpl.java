package com.alevel.facade.impl;

import com.alevel.facade.RegistrationFacade;
import com.alevel.persistence.entity.user.User;
import com.alevel.persistence.type.RoleType;
import com.alevel.service.UserCrudService;
import com.alevel.web.dto.request.register.AuthDto;
import org.springframework.stereotype.Service;

@Service
public class RegistrationFacadeImpl implements RegistrationFacade {

    private final UserCrudService userCrudService;

    public RegistrationFacadeImpl(UserCrudService userCrudService) {
        this.userCrudService = userCrudService;
    }

    @Override
    public void registration(AuthDto dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRoleType(RoleType.ROLE_USER);
        userCrudService.create(user);
    }
}
