package com.alevel.service;

import com.alevel.persistence.entity.user.User;

public interface UserCrudService extends BaseCrudService<User> {
    Long findByEmail(String email);
}
