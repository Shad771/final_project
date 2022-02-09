package com.alevel.persistence.repository.user;

import com.alevel.persistence.entity.user.User;
import com.alevel.persistence.repository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository<U extends User> extends BaseRepository<U> {

    U findByEmail(String email);

    boolean existsByEmail(String email);
}
