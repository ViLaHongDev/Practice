package com.practice.spsecurityjwt.repository;

import com.practice.spsecurityjwt.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    Optional<UserEntity> findUserByUsername(String username);
    boolean existsByUsername(String username);
}
