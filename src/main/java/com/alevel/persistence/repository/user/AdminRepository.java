package com.alevel.persistence.repository.user;

import com.alevel.persistence.entity.user.Admin;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends UserRepository<Admin> { }
