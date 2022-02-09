package com.alevel.service.impl;

import com.alevel.exception.EntityExistException;
import com.alevel.persistence.crud.CrudRepositoryHelper;
import com.alevel.persistence.datatable.DataTableRequest;
import com.alevel.persistence.datatable.DataTableResponse;
import com.alevel.persistence.entity.user.User;
import com.alevel.persistence.repository.user.UserRepository;
import com.alevel.service.UserCrudService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserCrudServiceImpl implements UserCrudService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;
    private final CrudRepositoryHelper<User, UserRepository<User>> crudRepositoryHelper;

    public UserCrudServiceImpl(
            BCryptPasswordEncoder bCryptPasswordEncoder,
            UserRepository userRepository, CrudRepositoryHelper<User, UserRepository<User>> crudRepositoryHelper) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
        this.crudRepositoryHelper = crudRepositoryHelper;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
    public void create(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new EntityExistException("this personal is exist");
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        crudRepositoryHelper.create(userRepository, user);
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public DataTableResponse<User> findAll(DataTableRequest request) {
        return null;
    }

    @Override
    public Long findByEmail(String email) {
        return userRepository.findByEmail(email).getId();
    }
}
